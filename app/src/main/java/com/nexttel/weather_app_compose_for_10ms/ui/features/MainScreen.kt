package com.nexttel.weather_app_compose_for_10ms.ui.features

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nexttel.weather_app_compose_for_10ms.models.SearchWidgetState
import com.nexttel.weather_app_compose_for_10ms.models.WeatherUiState
import com.nexttel.weather_app_compose_for_10ms.ui.features.weatherScreen.RequestForLocationPermissionScreen
import com.nexttel.weather_app_compose_for_10ms.ui.features.weatherScreen.WeatherScreen
import com.nexttel.weather_app_compose_for_10ms.ui.features.weatherScreen.WeatherTopAppBar
import com.nexttel.weather_app_compose_for_10ms.ui.theme.CloudyWhite
import com.nexttel.weather_app_compose_for_10ms.ui.viewmodel.WeatherViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = hiltViewModel(),
    onPermissionClick: () -> Unit,
) {
    val locationPermissionState: Boolean by viewModel.locationPermissionState
    val uiState: WeatherUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchWidgetState by viewModel.searchWidgetState
    val searchTextState by viewModel.searchTextState
    val location by viewModel.location.collectAsStateWithLifecycle(
        Pair(
            "Fetching location...", false
        )
    )

    Scaffold(
        topBar = {
            WeatherTopAppBar(searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = { viewModel.updateSearchTextState(it) },
                onCloseClicked = { viewModel.updateSearchWidgetState(SearchWidgetState.CLOSED) },
                onSearchClicked = { viewModel.getWeather(it) },
                onSearchTriggered = { viewModel.updateSearchWidgetState(SearchWidgetState.OPENED) })
        },
        content = { padding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                color = CloudyWhite
            ) {
                if (locationPermissionState || uiState.weather != null) {
                    WeatherScreen(
                        uiState = uiState, viewModel = viewModel, modifier = modifier
                    )
                } else {
                    RequestForLocationPermissionScreen(onPermissionClick)
                }
            }
        },
    )
}

