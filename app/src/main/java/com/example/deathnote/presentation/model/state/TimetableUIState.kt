package com.example.deathnote.presentation.model.state

import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.util.DayOfWeek
import com.example.deathnote.presentation.model.util.WeekType
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class TimetableUIState(
    val curWeekType: WeekType = WeekType.ODD,
    val isSemesterTimeSet: Boolean = false,

    val bottomSheetState: Boolean = false,
    val bottomSheetSubject: Subject = Subject(),
    val bottomSheetDayOfWeek: DayOfWeek = DayOfWeek.MONDAY,
    val bottomSheetWeekType: WeekType = WeekType.ODD,
    val bottomSheetStartTime: String = "08:00",
    val bottomSheetEndTime: String = "09:20",
    val bottomSheetSubjectPickerState: Boolean = false,

    val bottomSheetTimePickerState: Boolean = false,
    val bottomSheetTimePickerStartPick: String = "start",

    val settingsBottomSheetState: Boolean = false,
    val settingsBottomSheetIsTimeSet: Boolean = false,
    val settingsBottomSheetHolidays: List<DayOfWeek> = emptyList(),
    val settingsBottomSheetStartDate: String = nowTime,
    val settingsBottomSheetEndDate: String = nowTime,
    val settingsBottomSheetFirstWeekType: WeekType = WeekType.ODD,
)

private val nowTime: String = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))