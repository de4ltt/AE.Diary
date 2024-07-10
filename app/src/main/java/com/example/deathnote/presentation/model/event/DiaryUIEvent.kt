package com.example.deathnote.presentation.model.event

import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.Subject

sealed class DiaryUIEvent {

    data object SetNextDaySubject: DiaryUIEvent()
    data object SetPreviousDaySubject: DiaryUIEvent()
    data class SetDaySubjectDismissed(val daySubject: Subject): DiaryUIEvent()

    data class SetDaySubjectStudentAbsent(val student: Student, val daySubject: Subject): DiaryUIEvent()
    data class SetDaySubjectStudentPresent(val student: Student, val daySubject: Subject): DiaryUIEvent()

    data class SetDaySubjectStudentAbsentRespectful(val student: Student, val daySubject: Subject): DiaryUIEvent()
    data class SetDaySubjectStudentPresentRespectful(val student: Student, val daySubject: Subject): DiaryUIEvent()

    data class SetDay(val day: Subject): DiaryUIEvent()
}
