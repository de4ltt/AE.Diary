package com.example.deathnote.domain.use_case.timetable.util

import com.example.deathnote.domain.use_case.timetable.DeleteTimetableUseCase
import com.example.deathnote.domain.use_case.timetable.GetAllTimetablesUseCase
import com.example.deathnote.domain.use_case.timetable.GetDataStoreDataUseCase
import com.example.deathnote.domain.use_case.timetable.SetSemesterTimeUseCase
import com.example.deathnote.domain.use_case.timetable.UpsertTimetableUseCase
import javax.inject.Inject

data class TimetableUseCasesImpl @Inject constructor(
    override val GetAllTimetablesUseCase: GetAllTimetablesUseCase,
    override val UpsertTimetableUseCase: UpsertTimetableUseCase,
    override val DeleteTimetableUseCase: DeleteTimetableUseCase,
    override val SetSemesterTimeUseCase: SetSemesterTimeUseCase,
    override val GetDataStoreDataUseCase: GetDataStoreDataUseCase
): TimetableUseCases