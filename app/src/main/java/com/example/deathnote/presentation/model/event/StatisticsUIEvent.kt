package com.example.deathnote.presentation.model.event

import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.Subject

sealed class StatisticsUIEvent {

    data class ChangeStudent(val student: List<Student>) : StatisticsUIEvent()
    data class ChangeSubject(val subject: List<Subject>) : StatisticsUIEvent()

    data object ChangeStudentDrawerState: StatisticsUIEvent()
    data object ChangeSubjectDrawerState: StatisticsUIEvent()
}