package com.example.deathnote.domain.repository

import com.example.deathnote.domain.model.WeekTypeDomain
import kotlinx.coroutines.flow.Flow

interface WeekTypeRepository {

    suspend fun getAllWeekTypes(): Flow<List<WeekTypeDomain>>
    suspend fun upsertWeekType(weekType: WeekTypeDomain)
    suspend fun deleteAllWeekType()
    suspend fun getWeekTypeByDay(day: String): Flow<WeekTypeDomain>
}