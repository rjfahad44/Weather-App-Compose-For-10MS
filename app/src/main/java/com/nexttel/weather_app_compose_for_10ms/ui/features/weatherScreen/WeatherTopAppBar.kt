package com.nexttel.weather_app_compose_for_10ms.ui.features.weatherScreen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nexttel.weather_app_compose_for_10ms.components.AppBarComponent
import com.nexttel.weather_app_compose_for_10ms.components.SearchBarComponent
import com.nexttel.weather_app_compose_for_10ms.models.SearchWidgetState
import com.nexttel.weather_app_compose_for_10ms.ui.theme.WeatherAppComposeFor10MSTheme

@Composable
fun WeatherTopAppBar(
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit
) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            AppBarComponent(
                onSearchClicked = onSearchTriggered
            )
        }

        SearchWidgetState.OPENED -> {
            SearchBarComponent(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked
            )
        }
    }
}


@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun WeatherTopAppBarPreview() {
    Surface {
        WeatherAppComposeFor10MSTheme {
            WeatherTopAppBar(
                searchWidgetState = SearchWidgetState.OPENED,
                searchTextState = "",
                onTextChange = {},
                onCloseClicked = {},
                onSearchClicked = {},
                onSearchTriggered = {}
            )
        }
    }
}