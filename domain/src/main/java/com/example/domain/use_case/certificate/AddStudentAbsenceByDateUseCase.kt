package com.example.deathnote.domain.use_case.certificate

import com.example.deathnote.domain.repository.AbsenceRepository
import javax.inject.Inject

class AddStudentAbsenceByDateUseCase @Inject constructor(
    private val absenceRepository: AbsenceRepository
) {

    suspend operator fun invoke(date: String, studentId: Int) =
        absenceRepository.addStudentAbsenceByDate(date, studentId)

}