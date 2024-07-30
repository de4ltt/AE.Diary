package com.example.deathnote.presentation.model

import com.example.deathnote.presentation.model.interfaces.PresentationModel
import com.example.deathnote.presentation.util.TimeFormatter.dateFormatter
import com.example.deathnote.presentation.util.TimeFormatter.nowDate

data class Timetable(
    val id: Int = 0,
    val date: String = nowDate.format(dateFormatter),
    val subjectId: Int = 0,
    val startTime: String = "08:00",
    val endTime: String = "09:00",
    val weekType: String = "odd",
    val isDismissed: Boolean = false
): PresentationModel