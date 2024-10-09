package com.ae_diary.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ae_diary.R
import com.ae_diary.presentation.ui.screen.destinations.CertificatesScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.DatabaseSettingsScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.DiaryScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.LanguageScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.SettingsScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.StatisticsScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.StudentsScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.SubjectsScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.TimetableScreenDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

sealed interface AppDestination {

    enum class SettingsTopBarDestinations(
        destinationSpec: DirectionDestinationSpec,
        @StringRes val label: Int,
        @DrawableRes val icon: Int = R.drawable.arrow_left
    ): AppDestination {

        SETTINGS(SettingsScreenDestination, R.string.diary_settings),
        GROUP_LIST(StudentsScreenDestination, R.string.group_list),
        SUBJECTS(SubjectsScreenDestination, R.string.subjects),
        TIMETABLE(TimetableScreenDestination, R.string.timetable),
        LANGUAGE(LanguageScreenDestination, R.string.app_language),
        DATABASE(DatabaseSettingsScreenDestination, R.string.data_io)

    }

    enum class MainScreenMenusDestinations(
        destinationSpec: DirectionDestinationSpec,
        @StringRes val label: Int,
        @DrawableRes val icon: Int?
    ): AppDestination {

        CERTIFICATES(CertificatesScreenDestination, R.string.certificates, R.drawable.plus),
        DIARY(DiaryScreenDestination, R.string.diary, R.drawable.clock),
        STATISTICS(StatisticsScreenDestination, R.string.statistics, null)
    }
}

fun AppDestination.getSettingsTopBarIcon(): Int? {
    return when (this) {
        is AppDestination.SettingsTopBarDestinations -> this.icon
        is AppDestination.MainScreenMenusDestinations -> this.icon
        else -> null
    }
}

fun AppDestination.getSettingsTopBarLabel(): Int? {
    return when (this) {
        is AppDestination.SettingsTopBarDestinations -> this.label
        is AppDestination.MainScreenMenusDestinations -> this.label
        else -> null
    }
}