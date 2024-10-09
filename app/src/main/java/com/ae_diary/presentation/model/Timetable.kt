package com.ae_diary.presentation.model

import com.ae_diary.presentation.model.interfaces.PresentationModel
import com.ae_diary.presentation.util.TimeFormatter.dateFormatter
import com.ae_diary.presentation.util.TimeFormatter.nowDate

data class Timetable(
    val id: Int = 0,
    val date: String = nowDate.format(dateFormatter),
    val subjectId: Int = 0,
    val startTime: String = "08:00",
    val endTime: String = "09:00",
    val weekType: String = "odd",
    val isDismissed: Boolean = false
): PresentationModel