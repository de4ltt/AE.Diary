package com.ae_diary.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.ae_diary.presentation.ui.screen.NavGraphs
import com.ae_diary.presentation.ui.screen.destinations.CertificatesScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.DatabaseSettingsScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.DiaryScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.LanguageScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.MainScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.SettingsScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.StatisticsScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.StudentsScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.SubjectsScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.TimetableScreenDestination
import com.ae_diary.presentation.ui.screen.main_screen.CertificatesScreen
import com.ae_diary.presentation.ui.screen.main_screen.DiaryScreen
import com.ae_diary.presentation.ui.screen.main_screen.MainScreen
import com.ae_diary.presentation.ui.screen.main_screen.SettingsScreen
import com.ae_diary.presentation.ui.screen.main_screen.StatisticsScreen
import com.ae_diary.presentation.ui.screen.settings.DatabaseSettingsScreen
import com.ae_diary.presentation.ui.screen.settings.LanguageScreen
import com.ae_diary.presentation.ui.screen.settings.StudentsScreen
import com.ae_diary.presentation.ui.screen.settings.SubjectsScreen
import com.ae_diary.presentation.ui.screen.settings.TimetableScreen
import com.ae_diary.presentation.ui.theme.settings.DeathNoteTheme
import com.ae_diary.presentation.viewmodel.CertificateViewModel
import com.ae_diary.presentation.viewmodel.DatabaseSettingsViewModel
import com.ae_diary.presentation.viewmodel.DiaryViewModel
import com.ae_diary.presentation.viewmodel.MainScreenViewModel
import com.ae_diary.presentation.viewmodel.StatisticsViewModel
import com.ae_diary.presentation.viewmodel.StudentViewModel
import com.ae_diary.presentation.viewmodel.SubjectViewModel
import com.ae_diary.presentation.viewmodel.TimetableViewModel
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