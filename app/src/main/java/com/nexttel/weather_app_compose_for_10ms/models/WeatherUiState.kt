package com.nexttel.weather_app_compose_for_10ms.models

import androidx.annotation.Keep

@Keep
data class WeatherUiState(
    val weather: Weather? = null,
    val isLoading: Boolean = false,
    val errorMessage: String = "",
)
