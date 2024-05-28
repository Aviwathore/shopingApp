package com.example.userinformation.formatNumber

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

fun formatToIndianNumberingSystem(number: Long): String {
    val formatter = NumberFormat.getInstance(Locale("en", "IN")) as DecimalFormat
    formatter.applyPattern("##,##,###")
    val formattedNumber = formatter.format(number)
    val currencySymbol = Currency.getInstance("INR").symbol
    return "$currencySymbol$formattedNumber"
}
