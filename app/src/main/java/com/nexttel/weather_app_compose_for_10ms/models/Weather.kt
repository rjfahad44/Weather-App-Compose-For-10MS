package com.nexttel.weather_app_compose_for_10ms.models

import androidx.annotation.Keep


@Keep
data class Weather(
    val temperature: Int,
    val date: String,
    val wind: Int,
    val humidity: Int,
    val feelsLike: Int,
    val condition: ForecastResponse.Current.Condition,
    val uv: Int,
    val name: String,
    val forecasts: List<Forecast>
)
