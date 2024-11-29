package com.nexttel.weather_app_compose_for_10ms.ui.features.weatherScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nexttel.weather_app_compose_for_10ms.components.LoadingProgressComponent
import com.nexttel.weather_app_compose_for_10ms.models.WeatherUiState
import com.nexttel.weather_app_compose_for_10ms.ui.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(
    uiState: WeatherUiState,
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel?,
) {
    when {
        uiState.isLoading -> {
            LoadingProgressComponent()
        }

        uiState.errorMessage.isNotEmpty() -> {
            WeatherErrorState(uiState = uiState, viewModel = viewModel)
        }

        else -> {
            WeatherSuccessState(modifier = modifier, uiState = uiState)
        }
    }
}