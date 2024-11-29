package com.nexttel.weather_app_compose_for_10ms.data.remote

import com.nexttel.weather_app_compose_for_10ms.models.ForecastResponse
import com.nexttel.weather_app_compose_for_10ms.utils.API_KEY
import com.nexttel.weather_app_compose_for_10ms.utils.DEFAULT_WEATHER_DESTINATION
import com.nexttel.weather_app_compose_for_10ms.utils.NUMBER_OF_DAYS
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast.json")
    suspend fun getWeatherForecastData(
        @Query("key") key: String = API_KEY,
        @Query("q") location: String = DEFAULT_WEATHER_DESTINATION,
        @Query("days") days: Int = NUMBER_OF_DAYS,
    ): ForecastResponse
}