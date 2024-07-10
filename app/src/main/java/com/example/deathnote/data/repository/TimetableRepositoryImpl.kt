package com.example.deathnote.data.repository

import com.example.deathnote.data.mapper.toDomain
import com.example.deathnote.data.mapper.toEntity
import com.example.deathnote.data.model.Timetables
import com.example.deathnote.data.repository.database.dao.TimetablesDAO
import com.example.deathnote.domain.model.TimetableDomain
import com.example.deathnote.domain.repository.TimetableRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TimetableRepositoryImpl @Inject constructor(
    private val timetableDao: TimetablesDAO
): TimetableRepository {

    override suspend fun getAllTimetables(): Flow<List<TimetableDomain>> =
        timetableDao.getAllTimetables().toDomain(Timetables::toDomain)

    override suspend fun getTimetablesByDay(day: String): Flow<List<TimetableDomain>> =
        timetableDao.getTimetablesByDay(day).toDomain(Timetables::toDomain)

    override suspend fun upsertTimetable(timetable: TimetableDomain) =
        timetableDao.upsertTimetable(timetable.toEntity())

    override suspend fun deleteTimetable(timetable: TimetableDomain) {
        timetableDao.deleteTimetable(timetable.toEntity())
    }
    override suspend fun deleteTimetablesBySubjectId(id: Int) =
        timetableDao.deleteTimetablesBySubjectId(id)
}