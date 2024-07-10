package com.example.deathnote.domain.use_case.timetable

import com.example.deathnote.domain.repository.TimetableRepository
import javax.inject.Inject

class GetTimetablesByDayUseCase @Inject constructor(
    private val timetableRepository: TimetableRepository
) {

    suspend operator fun invoke(day: String) =
        timetableRepository.getTimetablesByDay(day)

}