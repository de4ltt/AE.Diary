package com.ae_diary.domain.use_case.timetable

import com.ae_diary.domain.repository.TimetableRepository
import javax.inject.Inject

class GetAllTimetablesUseCase @Inject constructor(
    private val timetableRepository: TimetableRepository
) {

    suspend operator fun invoke() = timetableRepository.getAllTimetables()
}