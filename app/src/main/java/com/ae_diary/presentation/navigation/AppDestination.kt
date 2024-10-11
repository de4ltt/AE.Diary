package com.ae_diary.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ae_diary.R
import com.ae_diary.presentation.model.enums.DatabaseScreenMode
import com.ae_diary.presentation.ui.screen.destinations.CertificatesScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.DatabaseSettingsScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.DiaryScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.DirectionDestination
import com.ae_diary.presentation.ui.screen.destinations.LanguageScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.SettingsScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.StatisticsScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.StudentsScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.SubjectsScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.TimetableScreenDestination

sealed interface AppDestination {

    enum class MainScreenDestinations(
        @StringRes val title: Int,
        @StringRes val shortTitle: Int,
        @DrawableRes val expansionIcon: Int,
        @DrawableRes val indicationIcon: Int,
        @DrawableRes val functionIcon: Int? = null,
        @DrawableRes val backHandlerIcon: Int? = null,
        val destination: DirectionDestination
    ) : AppDestination {

        DIARY(
            title = R.string.diary,
            shortTitle = R.string.diary,
            indicationIcon = R.drawable.diary_tl,
            expansionIcon = R.drawable.diary_me,
            functionIcon = R.drawable.clock,
            destination = DiaryScreenDestination
        ),
        CERTIFICATES(
            title = R.string.certificates,
            shortTitle = R.string.certificates,
            indicationIcon = R.drawable.list_tl,
            expansionIcon = R.drawable.list_me,
            functionIcon = R.drawable.plus,
            destination = CertificatesScreenDestination
        ),
        STATISTICS(
            title = R.string.statistics,
            shortTitle = R.string.stats_bar,
            indicationIcon = R.drawable.stats_tl,
            expansionIcon = R.drawable.stats_me,
            destination = StatisticsScreenDestination
        ),
        SETTINGS(
            title = R.string.diary_settings,
            shortTitle = R.string.settings_bar,
            indicationIcon = R.drawable.settings_tl,
            expansionIcon = R.drawable.settings_me,
            backHandlerIcon = R.drawable.arrow_left,
            destination = SettingsScreenDestination
        )
    }

    enum class SettingsTopBarDestinations(
        @StringRes val title: Int,
        @StringRes val description: Int,
        @DrawableRes val indicationIcon: Int,
        @DrawableRes val backHandlerIcon: Int? = R.drawable.arrow_left,
        @DrawableRes val functionIcon: Int? = null,
        val destination: DirectionDestination
    ) : AppDestination {

        GROUP_LIST(
            title = R.string.group_list,
            description = R.string.to_be_or_not_to_be,
            indicationIcon = R.drawable.cat,
            destination = StudentsScreenDestination
        ),
        SUBJECTS(
            title = R.string.subjects,
            description = R.string.your_doom,
            indicationIcon = R.drawable.subjects,
            destination = SubjectsScreenDestination
        ),
        TIMETABLE(
            title = R.string.timetable,
            description = R.string.sorry_for_change,
            indicationIcon = R.drawable.timetable,
            destination = TimetableScreenDestination
        ),
        LANGUAGE(
            title = R.string.app_language,
            description = R.string.language_pardon,
            indicationIcon = R.drawable.language,
            destination = LanguageScreenDestination
        ),
        DATABASE(
            title = R.string.data_io,
            description = R.string.remember_me,
            indicationIcon = R.drawable.data_io,
            destination = DatabaseSettingsScreenDestination
        )
    }

    enum class SettingsDatabaseDestinations(
        @StringRes val title: Int,
        @DrawableRes val icon: Int,
        val screenMode: DatabaseScreenMode
    ) {

        EXPORT_LOCAL(
            title = R.string.data_export,
            icon = R.drawable.data_export,
            screenMode = DatabaseScreenMode.EXPORT
        ),
        IMPORT_LOCAL(
            title = R.string.data_import,
            icon = R.drawable.data_import,
            screenMode = DatabaseScreenMode.IMPORT
        )
    }
}

val AppDestination.topBarIcon
    get() = when (this) {
        AppDestination.MainScreenDestinations.SETTINGS -> AppDestination.MainScreenDestinations.SETTINGS.backHandlerIcon
        is AppDestination.MainScreenDestinations -> this.functionIcon
        is AppDestination.SettingsTopBarDestinations -> this.backHandlerIcon
        else -> throw IllegalArgumentException(enumExceptionMessage)
    }

val AppDestination.topBarLabel
    get() = when (this) {
        is AppDestination.SettingsTopBarDestinations -> this.title
        is AppDestination.MainScreenDestinations -> this.title
        else -> throw IllegalArgumentException(enumExceptionMessage)
    }

private const val enumExceptionMessage = "This enum class is not supported"