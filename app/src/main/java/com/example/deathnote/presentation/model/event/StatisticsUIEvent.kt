package com.example.deathnote.presentation.model.event

import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.interfaces.StatisticsMode

sealed class StatisticsUIEvent {

    data class ChangeStudent(val student: Student) : StatisticsUIEvent()
    data class ChangeSubject(val subject: Subject) : StatisticsUIEvent()

    data class ChangeMode(val mode: StatisticsMode) : StatisticsUIEvent()

    data object ChangeStudentDrawerState: StatisticsUIEvent()
    data object ChangeSubjectDrawerState: StatisticsUIEvent()
}