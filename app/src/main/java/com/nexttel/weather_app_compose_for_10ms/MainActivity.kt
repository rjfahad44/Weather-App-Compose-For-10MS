package com.nexttel.weather_app_compose_for_10ms

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.nexttel.weather_app_compose_for_10ms.ui.theme.WeatherAppComposeFor10MSTheme
import com.nexttel.weather_app_compose_for_10ms.ui.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            viewModel.getWeather()
            val uiState = viewModel.uiState.collectAsState()
            WeatherAppComposeFor10MSTheme {
                Log.d("fahad007", "${uiState.value}")
            }
        }
    }
}