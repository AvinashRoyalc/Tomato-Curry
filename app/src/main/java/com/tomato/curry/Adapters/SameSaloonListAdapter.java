package com.tomato.curry.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tomato.curry.Data.Utils;
import com.tomato.curry.Data.TcData;
import com.tomato.curry.R;
import com.tomato.curry.SaloonOverview;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class SameSaloonListAdapter extends RecyclerView.Adapter<SameSaloonListAdapter.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {


        public ImageView star1, star2, star3, star4, star5;
        public TextView tv_no, tv_saloonname, tv_saloonaddress;


        public ViewHolder(View v) {
            super(v);
            star1 = (ImageView) v.findViewById(R.id.star1);
            star2 = (ImageView) v.findViewById(R.id.star2);
            star3 = (ImageView) v.findViewById(R.id.star3);
            star4 = (ImageView) v.findViewById(R.id.star4);
            star5 = (ImageView) v.findViewById(R.id.star5);
            tv_no = (TextView) v.findViewById(R.id.tvSaloonno);
            tv_saloonname = (TextView) v.findViewById(R.id.tvSaloonname);
            tv_saloonaddress = (TextView) v.findViewById(R.id.tvSaloon_address);
        }

    }

    ArrayList<String> saloondata1, saloondata2;
    ArrayList<String> arrDownloadUrls = new ArrayList<>();
    public int saloonsize;
    public int stars1, stars2;
    public Context context;
    public int itempos;


    public SameSaloonListAdapter(Context context, ArrayList<String> saloondata1, ArrayList<String> saloondata2, int saloonsize) {
        this.saloonsize = saloonsize;
        this.saloondata1 = saloondata1;
        this.saloondata2 = saloondata2;
        this.context = context;

    }

    public SameSaloonListAdapter(Context context, ArrayList<String> saloondata1, int saloonsize) {
        this.saloonsize = saloonsize;
        this.saloondata1 = saloondata1;
        this.context = context;
    }

    @Override
    public SameSaloonListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.saloon_selection_item, parent, false);
        v.setOnClickListener(onClickListener);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TcData data = new TcData();
            if (itempos == 0) {
                arrDownloadUrls = data.getImageURLS(saloondata1.get(2));
            } else if (itempos == 1) {
                arrDownloadUrls = data.getImageURLS(saloondata2.get(2));
            }
            for (int i = 0; i < arrDownloadUrls.size(); i++) {
                String url = arrDownloadUrls.get(i);
                if (!TextUtils.isEmpty(Utils.isFileExist(url.split("/")[url.split("/").length - 1], Utils.getRootFolderPath(context))))
                    arrDownloadUrls.remove(i);
            }
            if (arrDownloadUrls.size() > 0)
                if (Utils.isConnected(context)) {
                    new DownLoadFileFromUrls(context).execute();
                } else {
                    // Show ALert

                }
            else
                callNextActivity();
        }
    };

    private void callNextActivity() {
        Intent intent = new Intent(context, SaloonOverview.class);
        if (itempos == 0) {
            intent.putExtra("saloonno", saloondata1.get(0));
            intent.putExtra("saloonname", saloondata1.get(1));
            intent.putExtra("saloonadd", saloondata1.get(2));

        } else if (itempos == 1) {
            intent.putExtra("saloonno", saloondata2.get(0));
            intent.putExtra("saloonname", saloondata2.get(1));
            intent.putExtra("saloonadd", saloondata2.get(2));
        }

        context.startActivity(intent);
    }

    private class DownLoadFileFromUrls extends AsyncTask<String, String, String> {
        final String root;
        final Context context;

        DownLoadFileFromUrls(Context context) {
            this.context = context;
            root = Utils.getRootFolderPath(context);
        }

        @Override
        protected void onPostExecute(String value) {
            Utils.pDialog.dismiss();
            if (!TextUtils.isEmpty(value)) {
                if (Utils.isConnected(context)) {
                    new DownLoadFileFromUrls(context).execute();
                } else {
                    // Show ALert
                }
            } else {
                callNextActivity();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                if (arrDownloadUrls.size() != 0) {
                    for (int i = 0; i < arrDownloadUrls.size(); i++) {
                        try {
                            String urll = arrDownloadUrls.get(i);
                            if (urll.startsWith("https")) {
                                urll = urll.replace("https", "http");
                            }
                            String[] arrChild = urll.split("/");
                            if (TextUtils.isEmpty(Utils.isFileExist(arrChild[arrChild.length - 1], root))) {
                                int count;
                                URL url = new URL(urll);
                                URLConnection conection = url.openConnection();
                                conection.connect();
                                int lenghtOfFile = conection.getContentLength();
                                InputStream input = new BufferedInputStream(url.openStream(), 8192);
                                File tempFile = new File(root, "temp" + arrChild[arrChild.length - 1]);
                                OutputStream output = new FileOutputStream(tempFile.getAbsolutePath());
                                byte data[] = new byte[1024];
                                long total = 0;
                                while ((count = input.read(data)) != -1) {
                                    total += count;
                                    output.write(data, 0, count);
                                }
                                output.flush();
                                output.close();
                                input.close();
                                File original = new File(root, arrChild[arrChild.length - 1]);
                                original.delete();
                                tempFile.renameTo(original);
                            }
                            String[] values = new String[1];
                            values[0] = "" + i;
                            publishProgress(values);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!Utils.isConnected(context)) {
                return "error";
            } else {
                return "";
            }
        }

        protected void onPreExecute() {
            Utils.startProgressBar(context);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            int index = Integer.parseInt(values[0]);
            Log.v("onProgressUpdate", "" + index);
            super.onProgressUpdate(values);
        }

    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == 0) {
            itempos = 0;
            for (int i = 0; i <= saloondata1.size(); i++) {
                if (i == 0) {
                    holder.tv_no.setText(saloondata1.get(i));
                } else if (i == 1) {
                    String s = "." + saloondata1.get(i);
                    holder.tv_saloonname.setText(s);
                } else if (i == 2) {
                    holder.tv_saloonaddress.setText(saloondata1.get(i));

                } else if (i == 3) {
                    stars1 = Integer.parseInt(saloondata1.get(i));
                }
            }
            switch (stars1) {
                case 1:
                    holder.star1.setImageResource(R.drawable.fstar);
                    break;
                case 2:
                    holder.star1.setImageResource(R.drawable.fstar);
                    holder.star2.setImageResource(R.drawable.fstar);
                    break;
                case 3:
                    holder.star1.setImageResource(R.drawable.fstar);
                    holder.star2.setImageResource(R.drawable.fstar);
                    holder.star3.setImageResource(R.drawable.fstar);
                    break;
                case 4:
                    holder.star1.setImageResource(R.drawable.fstar);
                    holder.star2.setImageResource(R.drawable.fstar);
                    holder.star3.setImageResource(R.drawable.fstar);
                    holder.star4.setImageResource(R.drawable.fstar);
                    break;
                case 5:
                    holder.star1.setImageResource(R.drawable.fstar);
                    holder.star2.setImageResource(R.drawable.fstar);
                    holder.star3.setImageResource(R.drawable.fstar);
                    holder.star4.setImageResource(R.drawable.fstar);
                    holder.star5.setImageResource(R.drawable.fstar);
                    break;
            }
        }
        if (position == 1) {
            itempos = 1;
            for (int i = 0; i <= saloondata2.size(); i++) {
                if (i == 0) {
                    holder.tv_no.setText(saloondata2.get(i));
                } else if (i == 1) {
                    String s = "." + saloondata2.get(i);
                    holder.tv_saloonname.setText(s);

                } else if (i == 2) {
                    holder.tv_saloonaddress.setText(saloondata2.get(i));

                } else if (i == 3) {
                    stars2 = Integer.parseInt(saloondata2.get(i));
                }
            }
            switch (stars2) {
                case 1:
                    holder.star1.setImageResource(R.drawable.fstar);
                    break;
                case 2:
                    holder.star1.setImageResource(R.drawable.fstar);
                    holder.star2.setImageResource(R.drawable.fstar);
                    break;
                case 3:
                    holder.star1.setImageResource(R.drawable.fstar);
                    holder.star2.setImageResource(R.drawable.fstar);
                    holder.star3.setImageResource(R.drawable.fstar);
                    break;
                case 4:
                    holder.star1.setImageResource(R.drawable.fstar);
                    holder.star2.setImageResource(R.drawable.fstar);
                    holder.star3.setImageResource(R.drawable.fstar);
                    holder.star4.setImageResource(R.drawable.fstar);
                    break;
                case 5:
                    holder.star1.setImageResource(R.drawable.fstar);
                    holder.star2.setImageResource(R.drawable.fstar);
                    holder.star3.setImageResource(R.drawable.fstar);
                    holder.star4.setImageResource(R.drawable.fstar);
                    holder.star5.setImageResource(R.drawable.fstar);
                    break;
            }

        }


    }

    @Override
    public int getItemCount() {
        return saloonsize;
    }
}
