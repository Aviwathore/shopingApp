package com.example.userinformation

import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

fun sendPushNotification(token: String, title: String, description: String, data: Map<String, String> = emptyMap()) {
    Log.d("TAG", "sendPushNotification: -----------------$token===> $title-->$description")
    val url = "https://fcm.googleapis.com/fcm/send"

    val bodyJSON = JSONObject()
    bodyJSON.put("to", token)

    val notificationJSON = JSONObject()
    notificationJSON.put("title", title)
    notificationJSON.put("description", description)
    notificationJSON.put("sound", "social_notification_sound.wav")

    bodyJSON.put("notification", notificationJSON)
    bodyJSON.put("data", JSONObject(data))

    val request = Request.Builder().url(url)
        .addHeader("Content-Type", "application/json")
        .post(
            bodyJSON.toString().toRequestBody("application/json; charset=utf-8".toMediaType())
        ).build()

    val client = OkHttpClient()

    client.newCall(request).enqueue(
        object : Callback {
            override fun onResponse(call: Call, response: Response) {
                Log.d("TAG", "onResponse: ------------${response.body}")
            }

            override fun onFailure(call: Call, e: IOException) {
                println(e.message.toString())
            }
        }
    )
}