package com.example.deathnote.presentation.model.state

import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.Timetable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class DiaryUIState(
    val curDate: String = nowTime,

    val curTimetable: Timetable = Timetable(),
    val curSubject: Subject = Subject(),
    val isSubjectDismissed: Boolean = false,

    val isDatePickerOpen: Boolean = false
)

private val nowTime = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))