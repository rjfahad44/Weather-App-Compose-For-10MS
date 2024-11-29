package com.nexttel.weather_app_compose_for_10ms.models

import androidx.annotation.Keep

@Keep
data class Hour(
    val time: String,
    val icon: String,
    val temperature: String,
)
