package com.example.userinformation.vibretephone

import android.content.Context
import android.os.Vibrator

fun vibratePhone(context: Context, duration: Long) {

    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    if (vibrator.hasVibrator()) {

        vibrator.vibrate(duration)

    }

}
