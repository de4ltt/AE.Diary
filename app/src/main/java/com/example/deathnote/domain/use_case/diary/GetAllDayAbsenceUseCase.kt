package com.example.deathnote.domain.use_case.diary

import com.example.deathnote.domain.repository.AbsenceRepository
import javax.inject.Inject

class GetAllDayAbsenceUseCase @Inject constructor(
    private val absenceRepository: AbsenceRepository
) {

    suspend operator fun invoke(date: String) =
        absenceRepository.getAllDayAbsences(date)

}