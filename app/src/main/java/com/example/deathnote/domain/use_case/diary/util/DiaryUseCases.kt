package com.example.deathnote.domain.use_case.diary.util

import com.example.deathnote.domain.use_case.diary.AddStudentAbsenceUseCase
import com.example.deathnote.domain.use_case.diary.AddStudentRespectfulAbsenceUseCase
import com.example.deathnote.domain.use_case.diary.DeleteStudentAbsenceUseCase
import com.example.deathnote.domain.use_case.timetable.UpsertTimetableUseCase

sealed interface DiaryUseCases {
    val AddStudentAbsenceUseCase: AddStudentAbsenceUseCase
    val AddStudentRespectfulAbsenceUseCase: AddStudentRespectfulAbsenceUseCase
    val DeleteStudentAbsenceUseCase: DeleteStudentAbsenceUseCase

    val UpsertTimetableUseCase: UpsertTimetableUseCase
}