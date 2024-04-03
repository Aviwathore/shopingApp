package com.example.userinformation.dashboard.service;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

public class ChannelServiceClass extends Application {
    public static String myChannelId = "MyChannel";

    @Override
    public void onCreate() {
        super.onCreate();

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

        NotificationChannel setNotificationChannel = new NotificationChannel(
                // this all are prototype
                myChannelId, "Service Example",

                // set a priority
                NotificationManager.IMPORTANCE_DEFAULT
        );

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(setNotificationChannel);
    }
}
