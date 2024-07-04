package com.example.deathnote.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.deathnote.presentation.ui.screen.main_screen.CertificatesScreen
import com.example.deathnote.presentation.ui.screen.settings.LanguageScreen
import com.example.deathnote.presentation.ui.screen.main_screen.MainScreen
import com.example.deathnote.presentation.ui.screen.settings.NavGraphs
import com.example.deathnote.presentation.ui.screen.main_screen.SettingsScreen
import com.example.deathnote.presentation.ui.screen.settings.StudentsScreen
import com.example.deathnote.presentation.ui.screen.settings.SubjectsScreen
import com.example.deathnote.presentation.ui.screen.settings.TimetableScreen
import com.example.deathnote.presentation.ui.screen.settings.destinations.CertificatesScreenDestination
import com.example.deathnote.presentation.ui.screen.settings.destinations.LanguageScreenDestination
import com.example.deathnote.presentation.ui.screen.settings.destinations.MainScreenDestination
import com.example.deathnote.presentation.ui.screen.settings.destinations.SettingsScreenDestination
import com.example.deathnote.presentation.ui.screen.settings.destinations.StudentsScreenDestination
import com.example.deathnote.presentation.ui.screen.settings.destinations.SubjectsScreenDestination
import com.example.deathnote.presentation.ui.screen.settings.destinations.TimetableScreenDestination
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
        composable(MainScreenDestination) {
            MainScreen(navigator = destinationsNavigator)
        }
        composable(SettingsScreenDestination) {
            SettingsScreen(navigator = destinationsNavigator)
        }
        composable(SubjectsScreenDestination) {
            SubjectsScreen(navigator = destinationsNavigator)
        }
        composable(TimetableScreenDestination) {
            TimetableScreen(navigator = destinationsNavigator)
        }
        composable(StudentsScreenDestination) {
            StudentsScreen(navigator = destinationsNavigator)
        }
        composable(LanguageScreenDestination) {
            LanguageScreen(navigator = destinationsNavigator)
        }
        composable(CertificatesScreenDestination) {
            CertificatesScreen(navigator = destinationsNavigator)
        }
    }
}