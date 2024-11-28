package com.nexttel.weather_app_compose_for_10ms.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexttel.weather_app_compose_for_10ms.data.repository.WeatherRepository
import com.nexttel.weather_app_compose_for_10ms.models.SearchWidgetState
import com.nexttel.weather_app_compose_for_10ms.models.WeatherUiState
import com.nexttel.weather_app_compose_for_10ms.utils.ApiResponse
import com.nexttel.weather_app_compose_for_10ms.utils.DEFAULT_WEATHER_DESTINATION
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    init {
        getWeather()
    }

    private val _uiState: MutableStateFlow<WeatherUiState> = MutableStateFlow(WeatherUiState(isLoading = true))
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    fun getWeather(placeName: String = DEFAULT_WEATHER_DESTINATION) {
        weatherRepository.getWeatherForecastData(placeName).map { result ->
            when (result) {
                is ApiResponse.Success -> {
                    _uiState.value = WeatherUiState(weather = result.data)
                }

                is ApiResponse.Error -> {
                    _uiState.value = WeatherUiState(errorMessage = result.errorMessage)
                }

                ApiResponse.Loading -> {
                    _uiState.value = WeatherUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private val _searchWidgetState: MutableState<SearchWidgetState> = mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }


    private val _searchTextState: MutableState<String> = mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }
}