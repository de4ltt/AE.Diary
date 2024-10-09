package com.ae_diary.domain.use_case.timetable.util

import com.ae_diary.domain.use_case.subject.GetAllSubjectsUseCase
import com.ae_diary.domain.use_case.timetable.DeleteAllTimetablesUseCase
import com.ae_diary.domain.use_case.timetable.DeleteTimetableUseCase
import com.ae_diary.domain.use_case.timetable.GetAllTimetablesUseCase
import com.ae_diary.domain.use_case.timetable.GetDataStoreDataUseCase
import com.ae_diary.domain.use_case.timetable.SetSemesterTimeUseCase
import com.ae_diary.domain.use_case.timetable.UpsertTimetableUseCase
import javax.inject.Inject

data class TimetableUseCasesImpl @Inject constructor(
    override val GetAllTimetablesUseCase: GetAllTimetablesUseCase,
    override val UpsertTimetableUseCase: UpsertTimetableUseCase,
    override val DeleteTimetableUseCase: DeleteTimetableUseCase,
    override val SetSemesterTimeUseCase: SetSemesterTimeUseCase,
    override val GetDataStoreDataUseCase: GetDataStoreDataUseCase,
    override val DeleteAllTimetablesUseCase: DeleteAllTimetablesUseCase,
    override val GetAllSubjectsUseCase: GetAllSubjectsUseCase
): TimetableUseCases