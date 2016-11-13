package com.tomato.curry.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tomato.curry.Data.DownLoadFileFromUrls;
import com.tomato.curry.Data.TcData;
import com.tomato.curry.Data.Utils;
import com.tomato.curry.R;
import com.tomato.curry.SaloonOverview;
import com.tomato.curry.SaloonSelection;

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
                Utils.arrDownloadUrls = data.getImageURLS(saloondata1.get(2));
            } else if (itempos == 1) {
                Utils.arrDownloadUrls = data.getImageURLS(saloondata2.get(2));
            }
            for (int i = 0; i < Utils.arrDownloadUrls.size(); i++) {
                String url = Utils.arrDownloadUrls.get(i);
                if (!TextUtils.isEmpty(Utils.isFileExist(url.split("/")[url.split("/").length - 1], Utils.getRootFolderPath(context))))
                    Utils.arrDownloadUrls.remove(i);
            }
            if (Utils.arrDownloadUrls.size() > 0)
                if (Utils.isConnected(context)) {
                    new DownLoadFileFromUrls(context).execute();
                } else {
                    // Show ALert
                    Toast.makeText(context,"Please connect your internet to download data required before processing",Toast.LENGTH_LONG).show();
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
