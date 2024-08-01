package com.example.deathnote.domain.use_case.diary

import com.example.deathnote.domain.model.AbsenceDomain
import com.example.deathnote.domain.repository.AbsenceRepository
import javax.inject.Inject

class DeleteStudentAbsenceUseCase @Inject constructor(
    private val absenceRepository: AbsenceRepository
) {

    suspend operator fun invoke(absence: AbsenceDomain) =
        absenceRepository.deleteAbsence(absence)

}