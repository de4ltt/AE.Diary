package com.example.deathnote.domain.repository

import com.example.deathnote.domain.model.HolidayDomain
import kotlinx.coroutines.flow.Flow

interface HolidayRepository {

    suspend fun getAllHolidays(): Flow<List<HolidayDomain>>
    suspend fun addHoliday(holiday: HolidayDomain)
    suspend fun deleteHoliday(holiday: HolidayDomain)

}