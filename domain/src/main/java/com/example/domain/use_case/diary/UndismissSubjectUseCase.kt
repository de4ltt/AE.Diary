package com.example.ae_diary.domain.use_case.diary

import com.example.ae_diary.domain.model.TimetableDomain
import com.example.ae_diary.domain.repository.TimetableRepository
import javax.inject.Inject

class UndismissSubjectUseCase @Inject constructor(
    private val timetableRepository: TimetableRepository
) {

    suspend operator fun invoke(timetable: TimetableDomain) =
        timetableRepository.upsertTimetable(
            timetable.copy(
                isDismissed = false
            )
        )
}