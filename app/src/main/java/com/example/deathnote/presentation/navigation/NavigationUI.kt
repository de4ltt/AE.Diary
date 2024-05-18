package com.example.deathnote.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.deathnote.presentation.ui.screen.settings.NavGraphs
import com.example.deathnote.presentation.ui.screen.settings.SettingsScreen
import com.example.deathnote.presentation.ui.screen.settings.destinations.SettingsScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.ramcosta.composedestinations.spec.NavHostEngine

@Composable
fun NavigationUI(
    navHostEngine: NavHostEngine,
    navHostController: NavHostController
) {

    DestinationsNavHost(
        navGraph = NavGraphs.root,
        engine = navHostEngine,
        navController = navHostController
    ) {
        composable(SettingsScreenDestination) {
            SettingsScreen(navigator = destinationsNavigator)
        }
    }
}