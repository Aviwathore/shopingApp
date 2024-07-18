package com.example.userinformation.dashboard.service;


import static com.example.userinformation.dashboard.service.ChannelServiceClass.myChannelId;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;


public class ServiceExample extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

//    @SuppressLint("ForegroundServiceType")
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
////
////        Intent[] notificationIntent = new Intent[]{new Intent(this, DashBoardActivity.class)};
////
////        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
////        Notification notification =new NotificationCompat.Builder(this, myChannelId)
////                .setContentTitle("My Notification")
////                .setContentText("My noti")
////                .setSmallIcon(R.id.beauty)
////                .setContentIntent(pendingIntent).build();
////
////       startForeground(1,notification);
////        return START_NOT_STICKY;
//    }

}

