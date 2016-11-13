package com.tomato.curry.Data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

public class Utils {

    public static boolean NetworkState = false;
    public static CustomProgressDialog pDialog;
    public static ArrayList<String> arrDownloadUrls = new ArrayList<>();

    public static boolean isConnected(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
                if (networkInfo.isRoaming()) {
                    // Roaming
                }
                int netType = networkInfo.getType();
                if (netType == ConnectivityManager.TYPE_WIFI) {
                    NetworkState = networkInfo.isConnected();
                } else if (netType == ConnectivityManager.TYPE_MOBILE) {
                    NetworkState = networkInfo.isConnected();
                } else {
                    NetworkState = false;
                }
            } else {
                NetworkState = false;
            }
        } catch (Exception e) {
            NetworkState = true;
        }
        return NetworkState;
    }

    public static String getRootFolderPath(Context context) {
        File myDir = context.getFilesDir();
        File folder = new File(myDir, "byji");
        if (!folder.exists()) {
            folder.mkdirs();
            Log.e("directory", "created");
        }
        return folder.getAbsolutePath();
    }

    public static boolean isFileImage(String url) {
        return url.endsWith("png") || url.endsWith("PNG") || url.endsWith("JPG") || url.endsWith("jpg") || url.endsWith("JPEG") || url.endsWith("jpeg");
    }

    public static String isFileExist(String name, String root) {
        try {
            File yourDir = new File(root);
            for (File f : yourDir.listFiles()) {
                if (f.isFile()) {
                    if (name.equalsIgnoreCase(f.getName()))
                        return f.getAbsolutePath();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void startProgressBar(Context c) {
        if (pDialog != null) {
            if (pDialog.isShowing())
                pDialog.dismiss();
        }
        pDialog = new CustomProgressDialog(c, true);
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);
        pDialog.show();
    }


}
