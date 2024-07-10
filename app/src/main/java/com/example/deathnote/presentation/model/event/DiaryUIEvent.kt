package com.example.deathnote.presentation.model.event

import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.Subject

sealed class DiaryUIEvent {

    data class SetNextDaySubject(val daySubject: Subject): DiaryUIEvent()
    data class SetPreviousDaySubject(val daySubject: Subject): DiaryUIEvent()
    data class SetDaySubjectDismissed(val daySubject: Subject): DiaryUIEvent()
    data class UnsetDaySubjectDismissed(val daySubject: Subject): DiaryUIEvent()

    data class SetDaySubjectStudentAbsent(val student: Student, val daySubject: Subject, val date: String): DiaryUIEvent()
    data class SetDaySubjectStudentPresent(val student: Student, val daySubject: Subject, val date: String): DiaryUIEvent()

    data class SetDaySubjectStudentAbsentRespectful(val student: Student, val daySubject: Subject, val date: String): DiaryUIEvent()
    data class SetDaySubjectStudentPresentRespectful(val student: Student, val daySubject: Subject, val date: String): DiaryUIEvent()

    data class SetDay(val day: String): DiaryUIEvent()
}
