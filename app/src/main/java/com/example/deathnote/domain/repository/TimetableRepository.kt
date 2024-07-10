package com.example.deathnote.domain.repository

import com.example.deathnote.domain.model.TimetableDomain
import kotlinx.coroutines.flow.Flow

interface TimetableRepository {

    suspend fun getAllTimetables(): Flow<List<TimetableDomain>>
    suspend fun getTimetablesByDay(day: String): Flow<List<TimetableDomain>>
    suspend fun upsertTimetable(timetable: TimetableDomain)
    suspend fun deleteTimetable(timetable: TimetableDomain)
    suspend fun deleteTimetablesBySubjectId(id: Int)
}