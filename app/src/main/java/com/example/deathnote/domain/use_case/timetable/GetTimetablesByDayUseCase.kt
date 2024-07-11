package com.example.deathnote.domain.use_case.timetable

import com.example.deathnote.domain.repository.TimetableRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTimetablesByDayUseCase @Inject constructor(
    private val timetableRepository: TimetableRepository
) {

    suspend operator fun invoke(day: String): Flow<List<Int>> =
        timetableRepository.getTimetablesByDay(day)

}