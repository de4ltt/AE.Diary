package com.example.deathnote.domain.use_case.main_screen.util

import com.example.deathnote.domain.use_case.subject.GetAllSubjectsUseCase
import com.example.deathnote.domain.use_case.timetable.GetAllTimetablesUseCase
import com.example.deathnote.domain.use_case.timetable.GetDataStoreDataUseCase

sealed interface MainScreenUseCases {
    val GetAllTimetablesUseCase: GetAllTimetablesUseCase
    val GetAllSubjectsUseCase: GetAllSubjectsUseCase
    val GetDataStoreDataUseCase: GetDataStoreDataUseCase
}