package com.nexttel.weather_app_compose_for_10ms.data.repository

import com.nexttel.weather_app_compose_for_10ms.data.remote.WeatherApi
import com.nexttel.weather_app_compose_for_10ms.models.toWeather
import com.nexttel.weather_app_compose_for_10ms.utils.ApiResponse
import com.nexttel.weather_app_compose_for_10ms.models.Weather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
): WeatherRepository {
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
    override fun getWeatherForecastData(placeName: String) = flow {
        emit(ApiResponse.Loading)
        try {
            val result = weatherApi.getWeatherForecastData(placeName = placeName).toWeather()
            emit(ApiResponse.Success(result))
        } catch (exception: HttpException) {
            emit(ApiResponse.Error(exception.message.orEmpty()))
        } catch (exception: IOException) {
            emit(ApiResponse.Error("Please check your network connection and try again!"))
        }
    }.flowOn(dispatcher)

}