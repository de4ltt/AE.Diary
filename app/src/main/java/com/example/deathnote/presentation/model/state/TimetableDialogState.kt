package com.example.deathnote.presentation.model.state

import com.example.deathnote.presentation.model.Subject

data class TimetableDialogState(
    val isShown: Boolean = false,
    val isSubjectPickerShown: Boolean = false,
    val isTimePickerShown: Boolean = false,
    val pick: String = "Start",
    val subject: Subject = Subject(),
    val selectedWeekType: String = "O",
    val selectedDay: Int = 1,
    val startTime: String = "08:00",
    val endTime: String = "09:20"
)