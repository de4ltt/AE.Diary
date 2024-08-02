package com.example.ae_diary.presentation.model.state

import com.example.ae_diary.presentation.model.Subject
import com.example.ae_diary.presentation.model.enums.SettingsDatePickerState
import com.example.ae_diary.presentation.model.enums.TimetableBottomSheetTimePickerState
import com.example.ae_diary.presentation.model.util.DayOfWeek
import com.example.ae_diary.presentation.model.util.WeekType
import com.example.ae_diary.presentation.util.TimeFormatter.dateFormatter
import com.example.ae_diary.presentation.util.TimeFormatter.nowDate
import com.example.ae_diary.presentation.util.TimeFormatter.nowDateFormatted

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

    val bottomSheetTimePickerState: TimetableBottomSheetTimePickerState = TimetableBottomSheetTimePickerState.NONE,

    val settingBottomSheetDatePickerState: SettingsDatePickerState = SettingsDatePickerState.NONE,
    val settingsBottomSheetState: Boolean = false,
    val settingsBottomSheetIsTimeSet: Boolean = false,
    val settingsBottomSheetHolidays: List<DayOfWeek> = emptyList(),
    val settingsBottomSheetStartDate: String = nowDateFormatted,
    val settingsBottomSheetEndDate: String = nowDate.plusDays(14).format(dateFormatter),
    val settingsBottomSheetFirstWeekType: WeekType = WeekType.ODD,
)