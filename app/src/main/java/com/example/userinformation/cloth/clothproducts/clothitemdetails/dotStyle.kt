package com.example.userinformation.cloth.clothproducts.clothitemdetails

import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.widget.TextView

fun dotStyle(view: TextView, color: Int, size: Float) {
    val text = view.text.toString()

    val parts = text.split(" ", limit = 2)
    val spannableString = SpannableString(text)

    spannableString.setSpan(
        ForegroundColorSpan(color),

        0,
        parts[0].length,
        SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    spannableString.setSpan(
        RelativeSizeSpan(size),
        0,
        parts[0].length,
        SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
    )

//    view.setText(spannableString, false)
    view.text = spannableString
}