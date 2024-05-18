package com.example.deathnote.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.deathnote.R
import com.example.deathnote.presentation.ui.screen.settings.destinations.LanguageScreenDestination
import com.example.deathnote.presentation.ui.screen.settings.destinations.SettingsScreenDestination
import com.example.deathnote.presentation.ui.screen.settings.destinations.StudentsScreenDestination
import com.example.deathnote.presentation.ui.screen.settings.destinations.StyleScreenDestination
import com.example.deathnote.presentation.ui.screen.settings.destinations.SubjectsScreenDestination
import com.example.deathnote.presentation.ui.screen.settings.destinations.TimetableScreenDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

sealed interface AppDestination {

    enum class SettingsTopBarDestinations(
        destinationSpec: DirectionDestinationSpec,
        @StringRes val label: Int,
        @DrawableRes val icon: Int
    ) : AppDestination {

        SETTINGS(SettingsScreenDestination, R.string.diary_settings, R.drawable.arrow_left),
        GROUP_LIST(StudentsScreenDestination, R.string.group_list, R.drawable.arrow_left),
        SUBJECTS(SubjectsScreenDestination, R.string.subjects, R.drawable.arrow_left),
        TIMETABLE(TimetableScreenDestination, R.string.timetable, R.drawable.arrow_left),
        LANGUAGE(LanguageScreenDestination, R.string.app_language, R.drawable.arrow_left),
        STYLE(StyleScreenDestination, R.string.style, R.drawable.arrow_left),

    }
}

fun AppDestination.getSettingsTopBarIcon(): Int {
    return if (this is AppDestination.SettingsTopBarDestinations)
        this.icon else 0
}

fun AppDestination.getSettingsTopBarLabel(): Int {
    return if (this is AppDestination.SettingsTopBarDestinations)
        this.label else 0
}