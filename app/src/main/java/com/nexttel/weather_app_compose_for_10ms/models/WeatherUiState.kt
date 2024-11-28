package com.nexttel.weather_app_compose_for_10ms.models

data class WeatherUiState(
    val weather: Weather? = null,
    val isLoading: Boolean = false,
    val errorMessage: String = "",
)
