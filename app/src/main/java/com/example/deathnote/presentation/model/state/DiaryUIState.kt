package com.example.deathnote.presentation.model.state

import com.example.deathnote.presentation.model.Subject
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class DiaryUIState(
    val curDate: String = nowTime,

    val curSubject: Subject = Subject(),
    val isSubjectDismissed: Boolean = false,

    val isDatePickerOpen: Boolean = false
)

private val nowTime = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))