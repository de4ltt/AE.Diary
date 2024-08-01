package com.example.deathnote.domain.use_case.timetable

import com.example.deathnote.domain.repository.TimetableRepository
import javax.inject.Inject

class GetAllTimetablesUseCase @Inject constructor(
    private val timetableRepository: TimetableRepository
) {

    suspend operator fun invoke() =
        timetableRepository.getAllTimetables()

}