package com.example.deathnote.presentation.model.state

import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.interfaces.SettingsDatePickerState
import com.example.deathnote.presentation.model.util.DayOfWeek
import com.example.deathnote.presentation.model.util.WeekType
import com.example.deathnote.presentation.util.TimeFormatter.nowDateFormatted

data class TimetableUIState(
    val curWeekType: WeekType = WeekType.ODD,
    val isSemesterTimeSet: Boolean = false,

    val curPage: Int = 0,

    val bottomSheetState: Boolean = false,
    val bottomSheetSubject: Subject = Subject(),
    val bottomSheetDayOfWeek: DayOfWeek = DayOfWeek.MONDAY,
    val bottomSheetWeekType: WeekType = WeekType.ODD,
    val bottomSheetStartTime: String = "08:00",
    val bottomSheetEndTime: String = "09:20",
    val bottomSheetSubjectPickerState: Boolean = false,

    val bottomSheetTimePickerState: Boolean = false,
    val bottomSheetTimePickerStartPick: String = "start",

    val settingBottomSheetDatePickerState: SettingsDatePickerState = SettingsDatePickerState.NONE,
    val settingsBottomSheetState: Boolean = false,
    val settingsBottomSheetIsTimeSet: Boolean = false,
    val settingsBottomSheetHolidays: List<DayOfWeek> = emptyList(),
    val settingsBottomSheetStartDate: String = nowDateFormatted,
    val settingsBottomSheetEndDate: String = nowDateFormatted,
    val settingsBottomSheetFirstWeekType: WeekType = WeekType.ODD,
)