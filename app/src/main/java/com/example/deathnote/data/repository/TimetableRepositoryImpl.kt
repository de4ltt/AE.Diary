package com.example.deathnote.data.repository

import com.example.deathnote.data.mapper.toDomain
import com.example.deathnote.data.mapper.toEntity
import com.example.deathnote.data.repository.database.dao.TimetablesDAO
import com.example.deathnote.domain.model.TimetableDomain
import com.example.deathnote.domain.repository.TimetableRepository
import kotlinx.coroutines.flow.Flow

class TimetableRepositoryImpl(
    private val timetableDao: TimetablesDAO
): TimetableRepository {
    override suspend fun getAllTimetables(): Flow<List<TimetableDomain>> =
        timetableDao.getAllTimetables().toDomain()

    override suspend fun upsertTimetable(timetable: TimetableDomain) =
        timetableDao.upsertTimetable(timetable.toEntity())
}