package com.ae_diary.domain.use_case.timetable.util

import com.ae_diary.domain.use_case.subject.GetAllSubjectsUseCase
import com.ae_diary.domain.use_case.timetable.DeleteAllTimetablesUseCase
import com.ae_diary.domain.use_case.timetable.DeleteTimetableUseCase
import com.ae_diary.domain.use_case.timetable.GetAllTimetablesUseCase
import com.ae_diary.domain.use_case.timetable.GetDataStoreDataUseCase
import com.ae_diary.domain.use_case.timetable.SetSemesterTimeUseCase
import com.ae_diary.domain.use_case.timetable.UpsertTimetableUseCase

sealed interface TimetableUseCases {
    val GetAllTimetablesUseCase: GetAllTimetablesUseCase
    val UpsertTimetableUseCase: UpsertTimetableUseCase
    val DeleteTimetableUseCase: DeleteTimetableUseCase
    val SetSemesterTimeUseCase: SetSemesterTimeUseCase
    val GetDataStoreDataUseCase: GetDataStoreDataUseCase
    val DeleteAllTimetablesUseCase: DeleteAllTimetablesUseCase
    val GetAllSubjectsUseCase: GetAllSubjectsUseCase
}