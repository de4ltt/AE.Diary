package com.example.deathnote.domain.use_case.diary

import com.example.deathnote.domain.model.TimetableDomain
import com.example.deathnote.domain.repository.TimetableRepository
import javax.inject.Inject

class DismissSubjectUseCase @Inject constructor(
    private val timetableRepository: TimetableRepository
) {

    suspend operator fun invoke(timetable: TimetableDomain) =
        timetableRepository.upsertTimetable(
            timetable.copy(
                isDismissed = true
            )
        )
}