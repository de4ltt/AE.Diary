package com.example.deathnote.presentation.model

data class Timetable(
    val id: Int? = null,
    val dayOfWeek: String = "O_1",
    val subjectId: Int? = null,
    val startTime: String? = null,
    val endTime: String? = null
)