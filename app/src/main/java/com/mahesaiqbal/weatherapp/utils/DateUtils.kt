package com.mahesaiqbal.weatherapp.utils

import java.text.SimpleDateFormat
import java.util.*

fun dateFormat(timeStamp: Int): String? {
    try {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val netDate = Date(timeStamp.toLong())

        return sdf.format(netDate)
    } catch (e: Exception) {
        return e.toString()
    }
}

fun timeStampToDate(timeStamp: Int): String {
    val sdf = SimpleDateFormat("EEEE, dd MMM yyyy")
    val date = Date(timeStamp.toLong() * 1000)
    return sdf.format(date)
}