package com.example.deathnote.domain.use_case.timetable.util

import com.example.deathnote.domain.use_case.timetable.DeleteTimetableUseCase
import com.example.deathnote.domain.use_case.timetable.GetAllTimetablesUseCase
import com.example.deathnote.domain.use_case.timetable.GetDataStoreDataUseCase
import com.example.deathnote.domain.use_case.timetable.SetSemesterTimeUseCase
import com.example.deathnote.domain.use_case.timetable.UpsertTimetableUseCase

sealed interface TimetableUseCases {
    val GetAllTimetablesUseCase: GetAllTimetablesUseCase
    val UpsertTimetableUseCase: UpsertTimetableUseCase
    val DeleteTimetableUseCase: DeleteTimetableUseCase
    val SetSemesterTimeUseCase: SetSemesterTimeUseCase
    val GetDataStoreDataUseCase: GetDataStoreDataUseCase
}