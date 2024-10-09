package com.ae_diary.domain.use_case.main_screen.util

import com.ae_diary.domain.use_case.subject.GetAllSubjectsUseCase
import com.ae_diary.domain.use_case.timetable.GetAllTimetablesUseCase
import com.ae_diary.domain.use_case.timetable.GetDataStoreDataUseCase
import javax.inject.Inject

data class MainScreenUseCasesImpl @Inject constructor(
    override val GetAllTimetablesUseCase: GetAllTimetablesUseCase,
    override val GetAllSubjectsUseCase: GetAllSubjectsUseCase,
    override val GetDataStoreDataUseCase: GetDataStoreDataUseCase
): MainScreenUseCases
