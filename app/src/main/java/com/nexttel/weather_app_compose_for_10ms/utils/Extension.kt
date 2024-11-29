package com.nexttel.weather_app_compose_for_10ms.utils

import android.content.Context
import android.widget.Toast
import kotlin.time.Duration

fun Context.toast(message: String, duration:Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, message, duration).show()
}