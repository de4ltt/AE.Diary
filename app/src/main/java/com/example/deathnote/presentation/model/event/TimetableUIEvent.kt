package com.example.deathnote.presentation.model.event

import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.Timetable

sealed class TimetableUIEvent() {

    data object ChangeWeekType : TimetableUIEvent()
    data class UpsertTimetable(val timetable: Timetable) : TimetableUIEvent()
    data class DeleteTimetable(val timetable: Timetable) : TimetableUIEvent()

    data class ChangeDialogSubject(val subject: Subject) : TimetableUIEvent()
    data class ChangeDialogDayOfWeek(val dayOfWeek: Int) : TimetableUIEvent()
    data object ChangeSelectedWeekType : TimetableUIEvent()
    data class ChangeDialogStartTime(val startTime: String) : TimetableUIEvent()
    data class ChangeDialogEndTime(val endTime: String) : TimetableUIEvent()
    data class ChangeDialogState(val isShown: Boolean) : TimetableUIEvent()
    data class ChangeDialogSubjectPickerState(val isShown: Boolean) : TimetableUIEvent()

    data class ChangeTimePickerState(val state: Boolean): TimetableUIEvent()
    data class ChangePick(val pick: String): TimetableUIEvent()
}
