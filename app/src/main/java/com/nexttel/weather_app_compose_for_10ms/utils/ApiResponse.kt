package com.nexttel.weather_app_compose_for_10ms.utils

sealed class ApiResponse<out T : Any> {
    data object Loading : ApiResponse<Nothing>()
    data class Success<out T : Any>(val data: T) : ApiResponse<T>()
    data class Error(val errorMessage: String) : ApiResponse<Nothing>()
}
