package com.nexttel.weather_app_compose_for_10ms.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nexttel.weather_app_compose_for_10ms.R
import com.nexttel.weather_app_compose_for_10ms.ui.theme.CloudyWhite
import com.nexttel.weather_app_compose_for_10ms.ui.theme.RainyBlue
import com.nexttel.weather_app_compose_for_10ms.ui.theme.WeatherAppComposeFor10MSTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarComponent(
    onSearchClicked: () -> Unit,
    ) {
    TopAppBar(
        modifier = Modifier.height(56.dp)
        ,
        title = {

            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.inverseSurface
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = CloudyWhite,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
        ),
        actions = {
            IconButton(
                onClick = { onSearchClicked() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(R.string.content_description),
                    tint = MaterialTheme.colorScheme.inverseSurface
                )
            }
        }
    )
}


@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AppBarComponentPreview() {
    Surface {
        WeatherAppComposeFor10MSTheme {
            AppBarComponent(
                onSearchClicked = {},
            )
        }
    }
}