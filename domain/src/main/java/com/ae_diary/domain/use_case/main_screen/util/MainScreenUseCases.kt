package com.ae_diary.domain.use_case.main_screen.util

import com.ae_diary.domain.use_case.subject.GetAllSubjectsUseCase
import com.ae_diary.domain.use_case.timetable.GetAllTimetablesUseCase
import com.ae_diary.domain.use_case.timetable.GetDataStoreDataUseCase

sealed interface MainScreenUseCases {
    val GetAllTimetablesUseCase: GetAllTimetablesUseCase
    val GetAllSubjectsUseCase: GetAllSubjectsUseCase
    val GetDataStoreDataUseCase: GetDataStoreDataUseCase
}