package com.example.deathnote.domain.use_case.main_screen.util

import com.example.deathnote.domain.use_case.subject.GetAllSubjectsUseCase
import com.example.deathnote.domain.use_case.timetable.GetAllTimetablesUseCase
import javax.inject.Inject

data class MainScreenUseCasesImpl @Inject constructor(
    override val GetAllTimetablesUseCase: GetAllTimetablesUseCase,
    override val GetAllSubjectsUseCase: GetAllSubjectsUseCase
): MainScreenUseCases
