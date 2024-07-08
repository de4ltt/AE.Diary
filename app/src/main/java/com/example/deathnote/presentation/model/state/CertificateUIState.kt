package com.example.deathnote.presentation.model.state

import com.example.deathnote.presentation.model.Student
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class CertificateUIState(
    val isBottomSheetShown: Boolean = false,
    val isSelectStudentSheetShown: Boolean = false,
    val student: Student = Student(),
    val start: String = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
    val end: String = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
)