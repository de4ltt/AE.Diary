package com.example.deathnote.domain.use_case.timetable

import com.example.deathnote.domain.repository.TimetableRepository
import javax.inject.Inject

class SetSemesterTimeUseCase @Inject constructor(
    private val timetableRepository: TimetableRepository
) {

    suspend operator fun invoke(start: String, end: String, firstWeekType: String, holidays: String) =
        timetableRepository.setSemesterTime(start, end, firstWeekType, holidays)
}