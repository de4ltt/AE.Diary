package com.example.deathnote.presentation.model.event

import com.example.deathnote.presentation.model.Timetable

sealed class TimetableUIEvent() {

    data object ChangeWeekType : TimetableUIEvent()
    data class UpsertTimetable(val timetable: Timetable) : TimetableUIEvent()
    data class DeleteTimetable(val timetable: Timetable) : TimetableUIEvent()
}
