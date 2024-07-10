package com.example.deathnote.domain.use_case.diary

import com.example.deathnote.domain.model.WeekTypeDomain
import com.example.deathnote.domain.repository.WeekTypeRepository
import javax.inject.Inject

class AddWeekTypeUseCase @Inject constructor(
    private val weekTypeRepository: WeekTypeRepository
) {

    suspend operator fun invoke(weekType: WeekTypeDomain) =
        weekTypeRepository.upsertWeekType(weekType)

}