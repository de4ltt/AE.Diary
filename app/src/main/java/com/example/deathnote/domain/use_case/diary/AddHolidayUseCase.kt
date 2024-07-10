package com.example.deathnote.domain.use_case.diary

import com.example.deathnote.domain.model.HolidayDomain
import com.example.deathnote.domain.repository.HolidayRepository
import javax.inject.Inject

class AddHolidayUseCase @Inject constructor(
    private val holidayRepository: HolidayRepository
)  {

    suspend operator fun invoke(holiday: HolidayDomain) =
        holidayRepository.upsertHoliday(holiday)

}