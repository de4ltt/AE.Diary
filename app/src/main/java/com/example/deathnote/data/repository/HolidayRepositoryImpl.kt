package com.example.deathnote.data.repository

import com.example.deathnote.data.mapper.toDomain
import com.example.deathnote.data.mapper.toEntity
import com.example.deathnote.data.model.Holidays
import com.example.deathnote.data.repository.database.dao.HolidaysDAO
import com.example.deathnote.domain.model.HolidayDomain
import com.example.deathnote.domain.repository.HolidayRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HolidayRepositoryImpl @Inject constructor(
    private val holidayDAO: HolidaysDAO
): HolidayRepository {

    override suspend fun getAllHolidays(): Flow<List<HolidayDomain>> =
        holidayDAO.getAllHolidays().toDomain(Holidays::toDomain)

    override suspend fun addHoliday(holiday: HolidayDomain) =
        holidayDAO.addHoliday(holiday.toEntity())

    override suspend fun deleteHoliday(holiday: HolidayDomain) =
        holidayDAO.deleteHoliday(holiday.toEntity())

}