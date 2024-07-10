package com.example.deathnote.data.repository

import com.example.deathnote.data.mapper.toDomain
import com.example.deathnote.data.mapper.toEntity
import com.example.deathnote.data.model.WeekTypes
import com.example.deathnote.data.repository.database.dao.WeekTypesDAO
import com.example.deathnote.domain.model.WeekTypeDomain
import com.example.deathnote.domain.repository.WeekTypeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeekTypeRepositoryImpl @Inject constructor(
    private val weekTypesDAO: WeekTypesDAO
): WeekTypeRepository {

    override suspend fun getAllWeekTypes(): Flow<List<WeekTypeDomain>> =
        weekTypesDAO.getAllWeekTypes().toDomain(WeekTypes::toDomain)

    override suspend fun upsertWeekType(weekType: WeekTypeDomain) =
        weekTypesDAO.upsertWeekType(weekType.toEntity())

    override suspend fun deleteAllWeekType() =
        weekTypesDAO.deleteAllWeekType()
}