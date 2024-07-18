package com.example.userinformation

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService:FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d("TAG", "onNewToken: ---------------------- $token")
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        Log.d("TAG", "onMessageReceived: ---------------------- $message")
        super.onMessageReceived(message)

      if (message.data.isNotEmpty()){
          val data = message.data

          val title = data["title"]
          val description = data["description"]

          Log.d("TAG", "onMessageReceived: --------$title----------->$description")

//          val notification = Notification.Builder(this, CHANNEL_ID)
//
//              .setContentTitle(title)
//
//              .setContentText(description)
//
//              .setSmallIcon(R.drawable.ic_launcher_background)
//
//              .setAutoCancel(true)
//
//
//          val notificationManager = getSystemService(NotificationManager::class.java)
//
//          notificationManager.notify(12345, notification.build())
      }

    }



}