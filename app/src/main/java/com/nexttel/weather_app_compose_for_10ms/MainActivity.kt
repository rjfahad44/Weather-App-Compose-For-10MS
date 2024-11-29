package com.nexttel.weather_app_compose_for_10ms

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat
import com.nexttel.weather_app_compose_for_10ms.ui.features.MainScreen
import com.nexttel.weather_app_compose_for_10ms.ui.theme.WeatherAppComposeFor10MSTheme
import com.nexttel.weather_app_compose_for_10ms.ui.viewmodel.WeatherViewModel
import com.nexttel.weather_app_compose_for_10ms.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

//        window?.let {
//            it.statusBarColor = SkyBlue.toArgb()
//            val controller = WindowInsetsControllerCompat(it, it.decorView)
//            controller.isAppearanceLightStatusBars = false
//        }

        setContent {
            val context = LocalContext.current

            val permissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestMultiplePermissions()
            ) { permissions ->
                val fineGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
                val coarseGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false

                if (fineGranted || coarseGranted) {
                    viewModel.updateLocationPermissionState(true)
                    viewModel.fetchLocation()
                } else {
                    viewModel.updateLocationPermissionState(false)
                    toast("Location permission denied")
                }
            }

            LaunchedEffect(Unit) {
                requestForLocationPermission(context, permissionLauncher)
            }

            WeatherAppComposeFor10MSTheme {
                MainScreen{
                    requestForLocationPermission(context, permissionLauncher)
                }
            }
        }
    }

    private fun requestForLocationPermission(
        context: Context,
        permissionLauncher: ManagedActivityResultLauncher<Array<String>, Map<String, @JvmSuppressWildcards Boolean>>
    ) {
        when {
            ContextCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                viewModel.updateLocationPermissionState(true)
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
}