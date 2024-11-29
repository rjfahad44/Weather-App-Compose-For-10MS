package com.nexttel.weather_app_compose_for_10ms.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController

//
//val LightColorScheme = lightColorScheme(
//    primary = SkyBlue,
//    secondary = SunshineYellow,
//    background = WhiteSmoke,
//    surface = LightGrey,
//    error = StormRed,
//    onPrimary = WhiteSmoke,
//    onSecondary = DarkGrey,
//    onBackground = DarkGrey,
//    onSurface = DeepBlue,
//    onError = WhiteSmoke,
//)
//
//val DarkColorScheme = darkColorScheme(
//    primary = DeepBlue,
//    secondary = SunsetOrange,
//    background = DarkGrey,
//    surface = RainyBlue,
//    error = StormRed,
//    onPrimary = LightGrey,
//    onSecondary = WhiteSmoke,
//    onBackground = WhiteSmoke,
//    onSurface = CloudyWhite,
//    onError = WhiteSmoke
//)

val LightColorScheme = lightColorScheme(
    primary = SkyBlue,
    secondary = SunshineYellow,
    background = WhiteSmoke,
    surface = LightGrey,
    error = StormRed,
    onPrimary = WhiteSmoke,
    onSecondary = DarkGrey,
    onBackground = DarkGrey,
    onSurface = DeepBlue,
    onError = WhiteSmoke,
)

val DarkColorScheme = darkColorScheme(
    primary = DeepBlue,
    secondary = SunshineYellow,
    background = DarkGrey,
    surface = LightGrey,
    error = StormRed,
    onPrimary = WhiteSmoke,
    onSecondary = DarkGrey,
    onBackground = WhiteSmoke,
    onSurface = DeepBlue,
    onError = WhiteSmoke,
)


@Composable
fun WeatherAppComposeFor10MSTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

    val systemUiController = rememberSystemUiController()

    if(darkTheme){
        systemUiController.setSystemBarsColor(
            color = DeepBlue,
            darkIcons = false
        )
    }else{
        systemUiController.setSystemBarsColor(
            color = CloudyWhite,
            darkIcons = false
        )
    }

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}