package com.example.aljadiproject.Notifications;

import android.app.Application;

import com.onesignal.OneSignal;

public class MyApplicationNotifications extends Application {
    private static final String ONESIGNAL_APP_ID = "d9edcb69-2c50-49e4-8026-b5f73423a04f";

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
    }
}
