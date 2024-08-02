package com.example.ae_diary.domain.use_case.timetable

import com.example.ae_diary.domain.repository.TimetableRepository
import javax.inject.Inject

class DeleteAllTimetablesUseCase @Inject constructor(
    private val timetableRepository: TimetableRepository
) {

    suspend operator fun invoke() {
        timetableRepository.deleteAllTimetables()
    }

}