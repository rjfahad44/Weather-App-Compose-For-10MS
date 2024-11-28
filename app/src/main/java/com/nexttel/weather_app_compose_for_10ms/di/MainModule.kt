package com.nexttel.weather_app_compose_for_10ms.di

import com.nexttel.weather_app_compose_for_10ms.data.remote.WeatherApi
import com.nexttel.weather_app_compose_for_10ms.data.repository.WeatherRepository
import com.nexttel.weather_app_compose_for_10ms.data.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Singleton
    @Provides
    fun provideWeatherRepository(weatherApi: WeatherApi): WeatherRepository = WeatherRepositoryImpl(weatherApi)
}