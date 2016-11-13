package com.tomato.curry.Data;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.tomato.curry.SaloonSelection;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by chandana on 13-11-2016.
 */

public class DownLoadFileFromUrls extends AsyncTask<String, String, String> {

    final String root;
    final Context context;

    public DownLoadFileFromUrls(Context context) {
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
                Toast.makeText(context,"Oops! please check your internet connection",Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(context,"Oops! please check your internet connection",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            if (Utils.arrDownloadUrls.size() != 0) {
                for (int i = 0; i < Utils.arrDownloadUrls.size(); i++) {
                    try {
                        String urll = Utils.arrDownloadUrls.get(i);
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

