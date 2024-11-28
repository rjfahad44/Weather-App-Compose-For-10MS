package com.nexttel.weather_app_compose_for_10ms.data.repository

import com.nexttel.weather_app_compose_for_10ms.models.Weather
import com.nexttel.weather_app_compose_for_10ms.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeatherForecastData(placeName: String): Flow<ApiResponse<Weather>>
}