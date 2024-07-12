package com.example.deathnote.domain.use_case.diary

import com.example.deathnote.domain.repository.WeekTypeRepository
import javax.inject.Inject

class GetWeekTypesUseCase @Inject constructor(
    private val weekTypeRepository: WeekTypeRepository
) {

    suspend operator fun invoke() =
        weekTypeRepository.getAllWeekTypes()

}