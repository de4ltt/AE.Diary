package com.example.deathnote.presentation.model.state

import com.example.deathnote.presentation.model.Subject
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class DiaryUIState(
    val curSubject: Subject = Subject(),
    val date: String = LocalDate.now().toString(),
    val isSettingsBottomSheetOpen: Boolean = false,
    val endOfSemester: String = LocalDate.now().format(
        DateTimeFormatter.ofPattern("dd.MM.yyyy")
    ),
    val startOfSemester: String = LocalDate.now().format(
        DateTimeFormatter.ofPattern("dd.MM.yyyy")
    ),
    val firstWeekType: String = "O",
    val isTimeSet: Boolean = false
)