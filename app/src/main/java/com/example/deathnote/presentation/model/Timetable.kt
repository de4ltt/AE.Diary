package com.example.deathnote.presentation.model

data class Timetable(
    val id: Int,
    val date: String,
    val subjectId: Int,
    val startTime: String,
    val endTime: String,
    val weekType: String,
    val isDismissed: Boolean
): PresentationModel