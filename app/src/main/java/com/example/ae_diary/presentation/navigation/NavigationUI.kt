package com.example.ae_diary.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.ae_diary.presentation.ui.screen.NavGraphs
import com.example.ae_diary.presentation.ui.screen.destinations.CertificatesScreenDestination
import com.example.ae_diary.presentation.ui.screen.destinations.DiaryScreenDestination
import com.example.ae_diary.presentation.ui.screen.destinations.LanguageScreenDestination
import com.example.ae_diary.presentation.ui.screen.destinations.MainScreenDestination
import com.example.ae_diary.presentation.ui.screen.destinations.SettingsScreenDestination
import com.example.ae_diary.presentation.ui.screen.destinations.StatisticsScreenDestination
import com.example.ae_diary.presentation.ui.screen.destinations.StudentsScreenDestination
import com.example.ae_diary.presentation.ui.screen.destinations.SubjectsScreenDestination
import com.example.ae_diary.presentation.ui.screen.destinations.TimetableScreenDestination
import com.example.ae_diary.presentation.ui.screen.main_screen.CertificatesScreen
import com.example.ae_diary.presentation.ui.screen.main_screen.DiaryScreen
import com.example.ae_diary.presentation.ui.screen.main_screen.MainScreen
import com.example.ae_diary.presentation.ui.screen.main_screen.SettingsScreen
import com.example.ae_diary.presentation.ui.screen.main_screen.StatisticsScreen
import com.example.ae_diary.presentation.ui.screen.settings.DatabaseSettingsScreen
import com.example.ae_diary.presentation.ui.screen.settings.LanguageScreen
import com.example.ae_diary.presentation.ui.screen.settings.StudentsScreen
import com.example.ae_diary.presentation.ui.screen.settings.SubjectsScreen
import com.example.ae_diary.presentation.ui.screen.settings.TimetableScreen
import com.example.ae_diary.presentation.ui.theme.settings.DeathNoteTheme
import com.example.ae_diary.presentation.viewmodel.CertificateViewModel
import com.example.ae_diary.presentation.viewmodel.DatabaseSettingsViewModel
import com.example.ae_diary.presentation.viewmodel.DiaryViewModel
import com.example.ae_diary.presentation.viewmodel.MainScreenViewModel
import com.example.ae_diary.presentation.viewmodel.StatisticsViewModel
import com.example.ae_diary.presentation.viewmodel.StudentViewModel
import com.example.ae_diary.presentation.viewmodel.SubjectViewModel
import com.example.ae_diary.presentation.viewmodel.TimetableViewModel
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
    statisticsViewModel: StatisticsViewModel,
    mainScreenViewModel: MainScreenViewModel,
    databaseSettingsViewModel: DatabaseSettingsViewModel,
    navHostEngine: NavHostEngine,
    navHostController: NavHostController
) {

    DestinationsNavHost(
        modifier = Modifier.background(DeathNoteTheme.colors.baseBackground),
        navGraph = NavGraphs.root,
        engine = navHostEngine,
        navController = navHostController
    ) {
        composable(MainScreenDestination) {
            MainScreen(
                mainScreenViewModel = mainScreenViewModel,
                timetableViewModel = timetableViewModel,
                navigator = destinationsNavigator
            )
        }
        composable(SettingsScreenDestination) {
            SettingsScreen(
                timetableViewModel = timetableViewModel,
                navigator = destinationsNavigator
            )
        }
        composable(SubjectsScreenDestination) {
            SubjectsScreen(
                navigator = destinationsNavigator,
                subjectViewModel = subjectViewModel
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
                timetableViewModel = timetableViewModel,
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
                statisticsViewModel = statisticsViewModel,
                studentViewModel = studentViewModel,
                subjectViewModel = subjectViewModel,
                navigator = destinationsNavigator
            )
        }
        composable(DatabaseSettingsScreenDestination) {
            DatabaseSettingsScreen(
                navigator = destinationsNavigator,
                databaseSettingsViewModel = databaseSettingsViewModel
            )
        }
    }
}