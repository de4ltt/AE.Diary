package com.example.deathnote.presentation.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Timetable(
    val id: Int = 0,
    val date: String = nowDate,
    val subjectId: Int = 0,
    val startTime: String = "08:00",
    val endTime: String = "09:00",
    val weekType: String = "odd",
    val isDismissed: Boolean = false
): PresentationModel

private val nowDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))