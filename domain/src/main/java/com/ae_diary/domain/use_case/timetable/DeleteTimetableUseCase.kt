package com.ae_diary.domain.use_case.timetable

import com.ae_diary.domain.model.TimetableDomain
import com.ae_diary.domain.repository.TimetableRepository
import javax.inject.Inject

class DeleteTimetableUseCase @Inject constructor(
    private val timetableRepository: TimetableRepository
) {

    suspend operator fun invoke(timetable: TimetableDomain) =
        timetableRepository.deleteTimetable(timetable)

}