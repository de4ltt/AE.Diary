package com.example.deathnote.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.deathnote.presentation.ui.screen.NavGraphs
import com.example.deathnote.presentation.ui.screen.destinations.CertificatesScreenDestination
import com.example.deathnote.presentation.ui.screen.destinations.DiaryScreenDestination
import com.example.deathnote.presentation.ui.screen.destinations.LanguageScreenDestination
import com.example.deathnote.presentation.ui.screen.destinations.MainScreenDestination
import com.example.deathnote.presentation.ui.screen.destinations.SettingsScreenDestination
import com.example.deathnote.presentation.ui.screen.destinations.StatisticsScreenDestination
import com.example.deathnote.presentation.ui.screen.destinations.StudentsScreenDestination
import com.example.deathnote.presentation.ui.screen.destinations.SubjectsScreenDestination
import com.example.deathnote.presentation.ui.screen.destinations.TimetableScreenDestination
import com.example.deathnote.presentation.ui.screen.main_screen.CertificatesScreen
import com.example.deathnote.presentation.ui.screen.main_screen.DiaryScreen
import com.example.deathnote.presentation.ui.screen.main_screen.MainScreen
import com.example.deathnote.presentation.ui.screen.main_screen.SettingsScreen
import com.example.deathnote.presentation.ui.screen.main_screen.StatisticsScreen
import com.example.deathnote.presentation.ui.screen.settings.LanguageScreen
import com.example.deathnote.presentation.ui.screen.settings.StudentsScreen
import com.example.deathnote.presentation.ui.screen.settings.SubjectsScreen
import com.example.deathnote.presentation.ui.screen.settings.TimetableScreen
import com.example.deathnote.presentation.viewmodel.CertificateViewModel
import com.example.deathnote.presentation.viewmodel.DiaryViewModel
import com.example.deathnote.presentation.viewmodel.StudentViewModel
import com.example.deathnote.presentation.viewmodel.SubjectViewModel
import com.example.deathnote.presentation.viewmodel.TimetableViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.ramcosta.composedestinations.spec.NavHostEngine

@Composable
fun NavigationUI(
    studentViewModel: StudentViewModel,
    subjectViewModel: SubjectViewModel,
    certificateViewModel: CertificateViewModel,
    timetableViewModel: TimetableViewModel,
    diaryViewModel: DiaryViewModel,
    navHostEngine: NavHostEngine,
    navHostController: NavHostController
) {

    DestinationsNavHost(
        navGraph = NavGraphs.root,
        engine = navHostEngine,
        navController = navHostController
    ) {
        composable(MainScreenDestination) {
            MainScreen(
                diaryViewModel = diaryViewModel,
                navigator = destinationsNavigator
            )
        }
        composable(SettingsScreenDestination) {
            SettingsScreen(
                diaryViewModel = diaryViewModel,
                navigator = destinationsNavigator
            )
        }
        composable(SubjectsScreenDestination) {
            SubjectsScreen(
                navigator = destinationsNavigator,
                subjectViewModel = subjectViewModel,
                diaryViewModel = diaryViewModel
            )
        }
        composable(TimetableScreenDestination) {
            TimetableScreen(
                subjectViewModel = subjectViewModel,
                timetableViewModel = timetableViewModel,
                navigator = destinationsNavigator
            )
        }
        composable(StudentsScreenDestination) {
            StudentsScreen(
                navigator = destinationsNavigator,
                studentViewModel = studentViewModel
            )
        }
        composable(LanguageScreenDestination) {
            LanguageScreen(navigator = destinationsNavigator)
        }
        composable(CertificatesScreenDestination) {
            CertificatesScreen(
                studentViewModel = studentViewModel,
                certificateViewModel = certificateViewModel,
                navigator = destinationsNavigator
            )
        }
        composable(DiaryScreenDestination) {
            DiaryScreen(
                studentViewModel = studentViewModel,
                diaryViewModel = diaryViewModel,
                navigator = destinationsNavigator
            )
        }
        composable(StatisticsScreenDestination) {
            StatisticsScreen(
                navigator = destinationsNavigator
            )
        }
    }
}