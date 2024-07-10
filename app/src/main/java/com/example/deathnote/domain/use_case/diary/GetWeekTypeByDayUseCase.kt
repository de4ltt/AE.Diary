package com.example.deathnote.domain.use_case.diary

import com.example.deathnote.domain.repository.WeekTypeRepository
import javax.inject.Inject

class GetWeekTypeByDayUseCase @Inject constructor(
    private val weekTypeRepository: WeekTypeRepository
) {

    suspend operator fun invoke(day: String) =
        weekTypeRepository.getWeekTypeByDay(day)

}