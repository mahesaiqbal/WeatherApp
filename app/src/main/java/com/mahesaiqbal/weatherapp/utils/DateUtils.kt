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