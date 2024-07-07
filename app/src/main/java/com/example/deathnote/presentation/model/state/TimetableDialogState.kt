package com.example.deathnote.presentation.model.state

import com.example.deathnote.presentation.model.Subject

data class TimetableDialogState(
    val isShown: Boolean = false,
    val isSubjectPickerShown: Boolean = false,
    val subject: Subject = Subject(),
    val selectedDay: String = "O_1",
    val startTime: String = "08:00",
    val endTime: String = "09:20"
)