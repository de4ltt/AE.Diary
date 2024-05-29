package com.example.deathnote.presentation.ui.screen.settings

import android.annotation.SuppressLint
import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.deathnote.activity.MainActivity
import com.example.deathnote.activity.util.loadLanguagePreference
import com.example.deathnote.presentation.model.Language
import com.example.deathnote.presentation.navigation.AppDestination
import com.example.deathnote.presentation.ui.cross_screen_ui.SettingsTopBar
import com.example.deathnote.presentation.ui.screen.settings.components.language_screen_ui.LanguageBar
import com.example.deathnote.presentation.ui.theme.util.DeathNoteTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("UnusedCrossfadeTargetStateParameter")
@Destination
@Composable
fun LanguageScreen(
    navigator: DestinationsNavigator,
    paddingValues: PaddingValues = PaddingValues(
        top = 50.dp,
        start = 25.dp,
        end = 25.dp
    )
) {

    val context: Context = LocalContext.current

    var curSelectedOption by remember { mutableStateOf(loadLanguagePreference(context)) }

    val languages = Language.entries.toList()

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

        val context: Context = LocalContext.current

        Crossfade(
            targetState = curSelectedOption,
            animationSpec = tween(0)
        ) {
            SettingsTopBar(
                destination = AppDestination.SettingsTopBarDestinations.LANGUAGE,
                onIconClick = {
                    navigator.popBackStack()
                }
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                items = languages,
            ) { language ->
                LanguageBar(
                    language = language,
                    isChosen = curSelectedOption == language.code,
                    onClick = {
                        if (curSelectedOption != language.code) {
                            curSelectedOption = language.code
                            (context as MainActivity).changeLanguage(language.code)
                        }
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.padding(bottom = 10.dp))
            }
        }
    }
}