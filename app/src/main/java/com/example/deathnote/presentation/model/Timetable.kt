package com.example.deathnote.presentation.model

data class Timetable(
    val id: Int = 0,
    val date: String = "",
    val subjectId: Int = 0,
    val startTime: String = "",
    val endTime: String = "",
    val weekType: String = "",
    val isDismissed: Boolean = false
): PresentationModel