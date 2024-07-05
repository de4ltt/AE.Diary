package com.example.deathnote.presentation.model


data class TimetableOld(
    val timetableID: Int?,
    val dayOfWeek: DayOfWeek,
    val weekType: WeekType,
    val daySubjects: DaySubjects
)
