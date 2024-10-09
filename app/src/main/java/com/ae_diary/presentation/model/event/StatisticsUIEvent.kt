package com.ae_diary.presentation.model.event

import com.ae_diary.presentation.model.Student
import com.ae_diary.presentation.model.Subject
import com.ae_diary.presentation.model.interfaces.StatisticsMode

sealed class StatisticsUIEvent {

    data class ChangeStudent(val student: Student) : StatisticsUIEvent()
    data class ChangeSubject(val subject: Subject) : StatisticsUIEvent()

    data class ChangeMode(val mode: StatisticsMode) : StatisticsUIEvent()

    data object ChangeStudentDrawerState: StatisticsUIEvent()
    data object ChangeSubjectDrawerState: StatisticsUIEvent()
}