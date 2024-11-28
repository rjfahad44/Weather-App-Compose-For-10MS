package com.nexttel.weather_app_compose_for_10ms.di

import com.google.gson.GsonBuilder
import com.nexttel.weather_app_compose_for_10ms.data.remote.WeatherApi
import com.nexttel.weather_app_compose_for_10ms.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providerRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(OkHttpClient.Builder().build())
        .build()

    @Singleton
    @Provides
    fun providerWeatherApi(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)

}