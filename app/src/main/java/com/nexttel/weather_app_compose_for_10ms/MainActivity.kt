package com.nexttel.weather_app_compose_for_10ms

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.nexttel.weather_app_compose_for_10ms.ui.theme.WeatherAppComposeFor10MSTheme
import com.nexttel.weather_app_compose_for_10ms.ui.viewmodel.WeatherViewModel
import com.nexttel.weather_app_compose_for_10ms.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppComposeFor10MSTheme {

                //viewModel.getWeather()
                val uiState = viewModel.uiState.collectAsState()
                val context = LocalContext.current
                val location by viewModel.location.collectAsState(Pair("Fetching location...", false))

                val permissionLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestMultiplePermissions()
                ) { permissions ->
                    val fineGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
                    val coarseGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false

                    if (fineGranted || coarseGranted) {
                        viewModel.fetchLocation()
                    } else {
                        toast("Location permission denied")
                    }
                }

                LaunchedEffect(Unit) {
                    when {
                        ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                                ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
                            viewModel.fetchLocation()
                        }

                        else -> {
                            permissionLauncher.launch(
                                arrayOf(
                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION
                                )
                            )
                        }
                    }
                }

                Log.d("fahad007", "${uiState.value}")
                Log.d("fahad007", "location : ${location.first}")
            }
        }
    }
}