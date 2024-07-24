package com.example.deathnote.presentation.model.event

import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.Timetable
import com.example.deathnote.presentation.model.util.DayOfWeek
import com.example.deathnote.presentation.model.util.WeekType

sealed class TimetableUIEvent {

    data object ChangeCurWeekType : TimetableUIEvent()
    data object UpsertTimetable : TimetableUIEvent()
    data class DeleteTimetable(val timetable: Timetable) : TimetableUIEvent()

    data class ChangeSemesterTime(val start: String, val end: String, val firstWeekType: WeekType) : TimetableUIEvent()

    data object ChangeBottomSheetState : TimetableUIEvent()
    data class ChangeBottomSheetSubject(val subject: Subject) : TimetableUIEvent()
    data class ChangeBottomSheetDayOfWeek(val dayOfWeek: DayOfWeek) : TimetableUIEvent()
    data object ChangeBottomSheetWeekType : TimetableUIEvent()
    data class ChangeBottomSheetStartTime(val startTime: String) : TimetableUIEvent()
    data class ChangeBottomSheetEndTime(val endTime: String) : TimetableUIEvent()

    data object ChangeBottomSheetSubjectPickerState: TimetableUIEvent()
    data object ChangeBottomSheetTimePickerState: TimetableUIEvent()
    data class ChangeBottomSheetStartTimePicker(val pick: String): TimetableUIEvent()

    data class IdleBottomSheet(val dayOfWeek: DayOfWeek) : TimetableUIEvent()

    data object ChangeSettingsScreenBottomSheetState: TimetableUIEvent()
    data class SettingsBottomSheetAddHoliday(val dayOfWeek: DayOfWeek) : TimetableUIEvent()
    data class SettingsBottomSheetDeleteHoliday(val dayOfWeek: DayOfWeek) : TimetableUIEvent()
}
