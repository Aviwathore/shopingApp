package com.example.userinformation.informationform

import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView

fun highlightStar(view: TextView, color: Int) {
    val text = view.text.toString()

    val parts = text.split(" ", limit = 2)
    val spannableString = SpannableString(text)

    spannableString.setSpan(
        ForegroundColorSpan(color),
        0,
        parts[0].length,
        SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
    )


//    view.setText(spannableString, false)
    view.text = spannableString
}
