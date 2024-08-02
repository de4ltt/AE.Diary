package com.example.ae_diary.presentation.model.state

import com.example.ae_diary.presentation.model.Student
import com.example.ae_diary.presentation.model.Subject
import com.example.ae_diary.presentation.model.interfaces.StatisticsMode

/**
 * @param mode
 * 0: 1 student, 1+ subject |
 * 1: 1+ student, 1 subject |
 * 2: 1+ student, 1+ subject
 */
data class StatisticsUIState(
    val mode: StatisticsMode = StatisticsMode.AllStudentsAllSubjects,
    val isSubjectDrawerOpen: Boolean = false,
    val isStudentDrawerOpen: Boolean = false,
    val curSubject: Subject = Subject(),
    val curStudent: Student = Student()
)