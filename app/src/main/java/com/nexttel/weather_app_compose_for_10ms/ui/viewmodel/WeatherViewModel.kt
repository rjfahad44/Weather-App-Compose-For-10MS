package com.nexttel.weather_app_compose_for_10ms.ui.viewmodel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.LocationServices
import com.nexttel.weather_app_compose_for_10ms.data.repository.WeatherRepository
import com.nexttel.weather_app_compose_for_10ms.models.SearchWidgetState
import com.nexttel.weather_app_compose_for_10ms.models.WeatherUiState
import com.nexttel.weather_app_compose_for_10ms.utils.ApiResponse
import com.nexttel.weather_app_compose_for_10ms.utils.DEFAULT_WEATHER_DESTINATION
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val weatherRepository: WeatherRepository
) : ViewModel() {

//    init {
//        getWeather()
//    }

    private val _locationPermissionState: MutableState<Boolean> = mutableStateOf(value = false)
    val locationPermissionState: State<Boolean> = _locationPermissionState
    fun updateLocationPermissionState(newValue: Boolean) {
        _locationPermissionState.value = newValue
    }

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    private val _location = MutableStateFlow<Pair<String, Boolean>>(value = Pair("", false))
    val location: StateFlow<Pair<String, Boolean>> = _location.asStateFlow()

    fun fetchLocation() {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    _location.value = Pair("${location.latitude},${location.longitude}", true)
                    getWeather(location = "${location.latitude},${location.longitude}")
                } else {
                    _location.value = Pair("Location not available", false)
                    getWeather()
                }
            }.addOnFailureListener {
                _location.value = Pair("Failed to get location", false)
                getWeather()
            }
        } else {
            _location.value = Pair("Permission denied", false)
            getWeather()
        }
    }


    private val _uiState: MutableStateFlow<WeatherUiState> = MutableStateFlow(WeatherUiState(isLoading = true))
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    fun getWeather(location: String = if (!_location.value.second) "23.8041,90.4152" else _location.value.first) {
        weatherRepository.getWeatherForecastData(location = location).map { result ->
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