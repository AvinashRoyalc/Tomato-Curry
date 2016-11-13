package com.tomato.curry;

import android.app.Application;

import com.onesignal.OneSignal;
import com.tomato.curry.onesignal.ExampleNotificationOpenedHandler;
import com.tomato.curry.onesignal.ExampleNotificationReceivedHandler;

/**
 * Created by chandana on 12-11-2016.
 */

public class TomataApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
         OneSignal.startInit(this)
                 .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler())
                 .setNotificationReceivedHandler(new ExampleNotificationReceivedHandler())
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .init();
    }
}
