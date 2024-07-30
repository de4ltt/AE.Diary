package com.example.deathnote.presentation.model.event

import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.Timetable
import com.example.deathnote.presentation.model.interfaces.SettingsDatePickerState
import com.example.deathnote.presentation.model.util.DayOfWeek

sealed class TimetableUIEvent {

    data object ChangeCurWeekType : TimetableUIEvent()
    data object UpsertTimetable : TimetableUIEvent()
    data class DeleteTimetable(val timetable: Timetable) : TimetableUIEvent()

    data object ChangeSemesterTime : TimetableUIEvent()
    data object IdleSemesterTime: TimetableUIEvent()

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

    data class ChangeSettingsScreenBottomSheetState(val state: SettingsDatePickerState): TimetableUIEvent()
    data class SettingsBottomSheetAddHoliday(val dayOfWeek: DayOfWeek) : TimetableUIEvent()
    data class SettingsBottomSheetDeleteHoliday(val dayOfWeek: DayOfWeek) : TimetableUIEvent()
    data class SettingsBottomSheetChangeSemesterStartTime(val time: String) : TimetableUIEvent()
    data class SettingsBottomSheetChangeSemesterEndTime(val time: String) : TimetableUIEvent()
    data object SettingsBottomSheetChangeFirstWeekType: TimetableUIEvent()
}
