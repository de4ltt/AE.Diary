package com.ae_diary.presentation.ui.screen.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ae_diary.R
import com.ae_diary.presentation.model.enums.DatabaseScreenMode
import com.ae_diary.presentation.model.event.DatabaseUIEvent
import com.ae_diary.presentation.navigation.AppDestination
import com.ae_diary.presentation.navigation.transition.GeneralTransition
import com.ae_diary.presentation.ui.common.TopBar
import com.ae_diary.presentation.ui.screen.settings.components.database_settings_screen_ui.DatabaseSettingsScreenPane
import com.ae_diary.presentation.ui.theme.settings.DeathNoteTheme
import com.ae_diary.presentation.viewmodel.DatabaseSettingsViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(style = GeneralTransition::class)
@Composable
fun DatabaseSettingsScreen(
    navigator: DestinationsNavigator,
    databaseSettingsViewModel: DatabaseSettingsViewModel,
    paddingValues: PaddingValues = PaddingValues(
        top = 50.dp,
        start = 25.dp,
        end = 25.dp
    )
) {

    val databaseUIState by databaseSettingsViewModel.databaseUIState.collectAsStateWithLifecycle()

    val isConstricted by remember {
        derivedStateOf {
            databaseUIState.mode == DatabaseScreenMode.IMPORT || databaseUIState.mode == DatabaseScreenMode.EXPORT
        }
    }

    val databaseDestinations = AppDestination.SettingsDatabaseDestinations.entries

    BackHandler {
        if (databaseUIState.mode == DatabaseScreenMode.DEFAULT)
            navigator.popBackStack()
        else
            databaseSettingsViewModel.onEvent(
                DatabaseUIEvent.ChangeDatabaseScreenMode(
                    DatabaseScreenMode.DEFAULT
                )
            )
    }

    Column(
        modifier = Modifier
            .background(color = DeathNoteTheme.colors.baseBackground)
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {

        TopBar(
            destination = AppDestination.SettingsTopBarDestinations.DATABASE,
            onIconClick = {
                navigator.popBackStack()
            },
            isConstricted = isConstricted,
            onConstrictedLabelId = R.string.diary_settings
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(DeathNoteTheme.colors.baseBackground),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(bottom = 10.dp),
        ) {
            items(databaseDestinations) { option ->
                option.apply {
                    DatabaseSettingsScreenPane(
                        titleId = title,
                        iconId = icon,
                        onClick = {
                            databaseSettingsViewModel.onEvent(
                                DatabaseUIEvent.ChangeDatabaseScreenMode(screenMode)
                            )
                        }
                    )
                }
            }
        }
    }
}