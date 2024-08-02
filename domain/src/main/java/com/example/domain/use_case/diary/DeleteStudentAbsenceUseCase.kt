package com.example.ae_diary.domain.use_case.diary

import com.example.ae_diary.domain.model.AbsenceDomain
import com.example.ae_diary.domain.repository.AbsenceRepository
import javax.inject.Inject

class DeleteStudentAbsenceUseCase @Inject constructor(
    private val absenceRepository: AbsenceRepository
) {

    suspend operator fun invoke(absence: AbsenceDomain) =
        absenceRepository.deleteAbsence(absence)

}