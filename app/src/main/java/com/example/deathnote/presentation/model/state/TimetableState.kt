package com.example.deathnote.presentation.model.state

import com.example.deathnote.presentation.model.Timetable

data class TimetableState(
    val timetable: Timetable = Timetable(),
    val weekType: String = "Odd"
)