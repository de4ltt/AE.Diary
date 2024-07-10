package com.example.deathnote.domain.use_case.diary

import com.example.deathnote.domain.repository.HolidayRepository
import javax.inject.Inject

class GetAllHolidaysUseCase @Inject constructor(
    private val holidayRepository: HolidayRepository
) {

    suspend operator fun invoke() = holidayRepository.getAllHolidays()

}