package com.example.deathnote.presentation.ui.screen.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.model.ColorPresentation
import com.example.deathnote.presentation.model.util.get
import com.example.deathnote.presentation.navigation.AppDestination
import com.example.deathnote.presentation.ui.cross_screen_ui.SettingsTopBar
import com.example.deathnote.presentation.ui.screen.settings.components.style_screen_ui.ColorStyleBar
import com.example.deathnote.presentation.ui.screen.settings.components.style_screen_ui.StyleInterthemePane
import com.example.deathnote.presentation.ui.theme.isDarkMode
import com.example.deathnote.presentation.ui.theme.switchDarkMode
import com.example.deathnote.presentation.ui.theme.switchWeekTypeScheme
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

    val colorTypeLists: List<ColorPresentation.ColorType> = ColorPresentation.ColorType.entries

    BackHandler {
        navigator.popBackStack()
    }

    Column(
        modifier = Modifier
            .background(color = DeathNoteTheme.colors.baseBackground)
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

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxHeight(),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            item {
                StyleInterthemePane(
                    onClick = { switchDarkMode(context) },
                    definingState = isDarkMode()
                )
            }

            item {
                StyleInterthemePane(
                    onClick = { switchWeekTypeScheme(context) },
                    definingState = true,
                    settingValue = R.string.week_type,
                    isOnValue = R.string.even,
                    isOffValue = R.string.odd
                )
            }

            items(
                items = colorTypeLists
            ) {
                ColorStyleBar(
                    color = it.get().first,
                    colorName = stringResource(id = it.get().second),
                    onClick = { /*TODO*/ },
                    onChange = { /*TODO*/ }
                )
            }

            items(2) {
                Spacer(modifier = Modifier.padding(bottom = 10.dp))
            }

        }
    }
}