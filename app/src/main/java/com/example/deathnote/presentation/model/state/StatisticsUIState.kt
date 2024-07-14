package com.example.deathnote.presentation.model.state

import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.Subject

data class StatisticsUIState(
    val mode: Int = 3,
    val isSubjectDrawerOpen: Boolean = false,
    val isStudentDrawerOpen: Boolean = false,
    val curSubject: List<Subject> = emptyList(),
    val curStudent: List<Student> = emptyList()
)