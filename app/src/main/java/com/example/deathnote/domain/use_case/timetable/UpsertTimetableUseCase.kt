package com.example.deathnote.domain.use_case.timetable

import com.example.deathnote.domain.model.TimetableDomain
import com.example.deathnote.domain.repository.TimetableRepository
import javax.inject.Inject

class UpsertTimetableUseCase @Inject constructor(
    private val timetableRepository: TimetableRepository
) {

    suspend operator fun invoke(timetable: TimetableDomain) =
        timetableRepository.upsertTimetable(timetable)
}