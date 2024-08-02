package com.example.ae_diary.domain.use_case.timetable

import com.example.ae_diary.domain.model.TimetableDomain
import com.example.ae_diary.domain.repository.TimetableRepository
import javax.inject.Inject

class UpsertTimetableUseCase @Inject constructor(
    private val timetableRepository: TimetableRepository
) {

    suspend operator fun invoke(timetable: TimetableDomain) =
        timetableRepository.upsertTimetable(timetable)
}