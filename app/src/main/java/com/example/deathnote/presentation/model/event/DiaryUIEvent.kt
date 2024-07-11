package com.example.deathnote.presentation.model.event

import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.Subject

sealed class DiaryUIEvent {

    data object SetNextDaySubject: DiaryUIEvent()
    data object SetPreviousDaySubject: DiaryUIEvent()
    data object SetDaySubjectDismissed: DiaryUIEvent()
    data object UnsetDaySubjectDismissed: DiaryUIEvent()

    data class SetDaySubjectStudentAbsent(val student: Student, val daySubject: Subject, val date: String = ""): DiaryUIEvent()
    data class SetDaySubjectStudentPresent(val student: Student, val daySubject: Subject, val date: String = ""): DiaryUIEvent()

    data class SetDaySubjectStudentAbsentRespectful(val student: Student, val daySubject: Subject, val date: String): DiaryUIEvent()
    data class SetDaySubjectStudentPresentRespectful(val student: Student, val daySubject: Subject, val date: String): DiaryUIEvent()

    data class SetDay(val day: String): DiaryUIEvent()
}
