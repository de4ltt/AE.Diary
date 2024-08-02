package com.example.ae_diary.domain.use_case.timetable.util

import com.example.ae_diary.domain.use_case.subject.GetAllSubjectsUseCase
import com.example.ae_diary.domain.use_case.timetable.DeleteAllTimetablesUseCase
import com.example.ae_diary.domain.use_case.timetable.DeleteTimetableUseCase
import com.example.ae_diary.domain.use_case.timetable.GetAllTimetablesUseCase
import com.example.ae_diary.domain.use_case.timetable.GetDataStoreDataUseCase
import com.example.ae_diary.domain.use_case.timetable.SetSemesterTimeUseCase
import com.example.ae_diary.domain.use_case.timetable.UpsertTimetableUseCase

sealed interface TimetableUseCases {
    val GetAllTimetablesUseCase: GetAllTimetablesUseCase
    val UpsertTimetableUseCase: UpsertTimetableUseCase
    val DeleteTimetableUseCase: DeleteTimetableUseCase
    val SetSemesterTimeUseCase: SetSemesterTimeUseCase
    val GetDataStoreDataUseCase: GetDataStoreDataUseCase
    val DeleteAllTimetablesUseCase: DeleteAllTimetablesUseCase
    val GetAllSubjectsUseCase: GetAllSubjectsUseCase
}