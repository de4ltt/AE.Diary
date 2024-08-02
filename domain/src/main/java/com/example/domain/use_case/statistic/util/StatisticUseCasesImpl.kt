package com.example.ae_diary.domain.use_case.statistic.util

import com.example.ae_diary.domain.use_case.diary.GetAllAbsenceUseCase
import com.example.ae_diary.domain.use_case.student.GetAllStudentsUseCase
import com.example.ae_diary.domain.use_case.subject.GetAllSubjectsUseCase
import com.example.ae_diary.domain.use_case.timetable.GetAllTimetablesUseCase
import javax.inject.Inject

data class StatisticUseCasesImpl @Inject constructor(
    override val GetAllAbsenceUseCase: GetAllAbsenceUseCase,
    override val GetAllTimetablesUseCase: GetAllTimetablesUseCase,
    override val GetAllSubjectsUseCase: GetAllSubjectsUseCase,
    override val GetAllStudentsUseCase: GetAllStudentsUseCase
): StatisticUseCases
