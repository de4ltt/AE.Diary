package com.example.deathnote.presentation.ui.screen.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.navigation.AppDestination
import com.example.deathnote.presentation.ui.cross_screen_ui.SettingsTopBar
import com.example.deathnote.presentation.ui.screen.settings.composable.style_screen_ui.ColorStyleBar
import com.example.deathnote.presentation.ui.screen.settings.composable.style_screen_ui.StyleInterthemePane
import com.example.deathnote.presentation.ui.theme.isDarkMode
import com.example.deathnote.presentation.ui.theme.switchDarkMode
import com.example.deathnote.presentation.ui.theme.util.DeathNoteTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun StyleScreen(
    navigator: DestinationsNavigator,
    paddingValues: PaddingValues = PaddingValues(
        top = 50.dp,
        start = 25.dp,
        end = 25.dp
    )
) {

    val context = LocalContext.current

    BackHandler {
        navigator.popBackStack()
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {

        SettingsTopBar(
            destination = AppDestination.SettingsTopBarDestinations.STYLE,
            onIconClick = {
                navigator.popBackStack()
            }
        )

        Column(
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {

            LazyVerticalGrid(
                modifier = Modifier.fillMaxHeight(),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                item {
                    StyleInterthemePane(
                        onClick = { /*TODO*/ },
                        definingState = isDarkMode()
                    )
                }

                item {
                    StyleInterthemePane(
                        onClick = { switchDarkMode(context) },
                        definingState = true,
                        settingValue = R.string.week_type,
                        isOnValue = R.string.even,
                        isOffValue = R.string.odd
                    )
                }

                items(5) {
                    ColorStyleBar(
                        color = DeathNoteTheme.colors.primary,
                        colorName = stringResource(id = R.string.primary),
                        onClick = { /*TODO*/ },
                        onChange = { /*TODO*/ }
                    )
                }
            }
        }
    }
}
