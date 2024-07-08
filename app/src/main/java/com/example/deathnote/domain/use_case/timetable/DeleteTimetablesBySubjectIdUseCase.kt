package com.example.deathnote.domain.use_case.timetable

import com.example.deathnote.domain.repository.TimetableRepository
import javax.inject.Inject

class DeleteTimetablesBySubjectIdUseCase @Inject constructor(
    private val timetablesRepository: TimetableRepository
){

    suspend operator fun invoke(id: Int) =
        timetablesRepository.deleteTimetablesBySubjectId(id)

}