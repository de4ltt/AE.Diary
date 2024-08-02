package com.example.ae_diary.domain.use_case.statistic.util

import com.example.ae_diary.domain.use_case.diary.GetAllAbsenceUseCase
import com.example.ae_diary.domain.use_case.student.GetAllStudentsUseCase
import com.example.ae_diary.domain.use_case.subject.GetAllSubjectsUseCase
import com.example.ae_diary.domain.use_case.timetable.GetAllTimetablesUseCase

sealed interface StatisticUseCases {
    val GetAllAbsenceUseCase: GetAllAbsenceUseCase
    val GetAllTimetablesUseCase: GetAllTimetablesUseCase
    val GetAllSubjectsUseCase: GetAllSubjectsUseCase
    val GetAllStudentsUseCase: GetAllStudentsUseCase
}