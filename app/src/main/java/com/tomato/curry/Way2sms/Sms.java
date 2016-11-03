package com.tomato.curry.Way2sms;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by CompIndia on 17-07-2016.
 */
public class Sms extends AsyncTask<String, Void, String> {


    public String doInBackground(String... urls) {
        String temp=null;
        try {
            URL u = new URL("http://appv5.way2sms.com");
            HttpURLConnection uc = (HttpURLConnection) u.openConnection();
            uc.setRequestMethod("POST");
            uc.setRequestProperty("User-Agent", "Dalvik/2.1.0 (Linux; U; Android 6.0.1; Nexus 7 Build/MOB30J)");
            uc.setRequestProperty("Content-Length", String.valueOf(0));
            uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            uc.setInstanceFollowRedirects(false);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(uc.getOutputStream()), true);
//        pw.print(content);
            pw.flush();
            pw.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            while ((temp = br.readLine()) != null) {
                System.out.println(temp);
            }
            br.close();
        } catch (ProtocolException e1) {
            e1.printStackTrace();
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return temp;
    }



    protected void onPostExecute(String temp) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }
}
//public class SMS
//{
//    public void send() throws IOException
//    {
////        String uid, String pwd, String phone, String msg
////        if ((uid == null) || (uid.length() == 0))
////        {
////            throw new IllegalArgumentException("User ID should be present.");
////        }
////        uid = URLEncoder.encode(uid, "UTF-8");
////
////        if ((pwd == null) || (pwd.length() == 0))
////        {
////            throw new IllegalArgumentException("Password should be present.");
////        }
////        pwd = URLEncoder.encode(pwd, "UTF-8");
////
////        if ((phone == null) || (phone.length() == 0))
////        {
////            throw new IllegalArgumentException("At least one phone number should be present.");
////        }
////        if ((msg == null) || (msg.length() == 0))
////        {
////            throw new IllegalArgumentException("SMS message should be present.");
////        }
////        msg = URLEncoder.encode(msg, "UTF-8");
////
////        Vector numbers = new Vector();
////
////        if (phone.indexOf(59) >= 0)
////        {
////            String[] pharr = phone.split(";");
////            for (String t : pharr)
////                try {
////                    numbers.add(Long.valueOf(t));
////                }
////                catch (NumberFormatException ex)
////                {
////                    throw new IllegalArgumentException("Give proper phone numbers.");
////                }
////        }
////        else
////        {
////            try
////            {
////                numbers.add(Long.valueOf(phone));
////            }
////            catch (NumberFormatException ex)
////            {
////                throw new IllegalArgumentException("Give proper phone numbers.");
////            }
////        }
////
////        if (numbers.size() == 0)
////        {
////            throw new IllegalArgumentException("At least one proper phone number should be present to send SMS.");
////        }
//        String temp = "";
////        String content = "username=" + uid + "&password=" + pwd;
//
//}