package com.ae_diary.domain.use_case.certificate

import com.ae_diary.domain.repository.AbsenceRepository
import javax.inject.Inject

class AddStudentAbsenceByDateUseCase @Inject constructor(
    private val absenceRepository: AbsenceRepository
) {

    suspend operator fun invoke(date: String, studentId: Int) =
        absenceRepository.addStudentAbsenceByDate(date, studentId)

}