package com.example.deathnote.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.deathnote.presentation.ui.screen.settings.LanguageScreen
import com.example.deathnote.presentation.ui.screen.settings.NavGraphs
import com.example.deathnote.presentation.ui.screen.settings.SettingsScreen
import com.example.deathnote.presentation.ui.screen.settings.StudentsScreen
import com.example.deathnote.presentation.ui.screen.settings.destinations.LanguageScreenDestination
import com.example.deathnote.presentation.ui.screen.settings.destinations.SettingsScreenDestination
import com.example.deathnote.presentation.ui.screen.settings.destinations.StudentsScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.ramcosta.composedestinations.spec.NavHostEngine

@Composable
fun NavigationUI(
    navHostEngine: NavHostEngine,
    navHostController: NavHostController,
    context: Context
) {

    DestinationsNavHost(
        navGraph = NavGraphs.root,
        engine = navHostEngine,
        navController = navHostController
    ) {
        composable(SettingsScreenDestination) {
            SettingsScreen(navigator = destinationsNavigator, context = context)
        }
        composable(StudentsScreenDestination) {
            StudentsScreen(navigator = destinationsNavigator)
        }
        composable(LanguageScreenDestination) {
            LanguageScreen(navigator = destinationsNavigator)
        }
    }
}