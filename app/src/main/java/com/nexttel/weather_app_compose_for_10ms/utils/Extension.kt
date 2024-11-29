package com.nexttel.weather_app_compose_for_10ms.utils

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.time.Duration

fun Context.toast(message: String, duration:Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, message, duration).show()
}

fun String.toFormattedDate(): String {
    return try {
        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputDateFormat = SimpleDateFormat("MMM d", Locale.getDefault())
        inputDateFormat.parse(this)?.let { date ->
            outputDateFormat.format(date)
        } ?: this
    } catch (e: Exception) {
        this
    }
}

fun String.toFormattedDay(): String? {
    return try {
        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = inputDateFormat.parse(this)
        date?.let {
            val outputDateFormat = SimpleDateFormat("EE", Locale.getDefault())
            outputDateFormat.format(it)
        }
    } catch (e: Exception) {
        null
    }
}
