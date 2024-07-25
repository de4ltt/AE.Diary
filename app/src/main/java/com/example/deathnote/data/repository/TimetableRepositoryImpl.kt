package com.example.deathnote.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.deathnote.DiaryApplication.Companion.END_TIME
import com.example.deathnote.DiaryApplication.Companion.FIRST_WEEK_TYPE
import com.example.deathnote.DiaryApplication.Companion.START_TIME
import com.example.deathnote.data.mapper.toDomain
import com.example.deathnote.data.mapper.toEntity
import com.example.deathnote.data.model.Timetables
import com.example.deathnote.data.repository.database.dao.TimetablesDAO
import com.example.deathnote.domain.model.TimetableDomain
import com.example.deathnote.domain.repository.TimetableRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TimetableRepositoryImpl @Inject constructor(
    private val timetableDao: TimetablesDAO,
    private val dataStore: DataStore<Preferences>
) : TimetableRepository {

    override suspend fun getAllTimetables(): Flow<List<TimetableDomain>> =
        timetableDao.getAllTimetables().toDomain(Timetables::toDomain)

    override suspend fun getTimetablesByDay(day: String): Flow<List<Int>> =
        timetableDao.getTimetablesByDay(day)

    override suspend fun upsertTimetable(timetable: TimetableDomain) =
        timetableDao.upsertTimetable(timetable.toEntity())

    override suspend fun deleteTimetable(timetable: TimetableDomain) {
        timetable.apply {
            timetableDao.deleteTimetable(
                date = date,
                subjectId = subjectId
            )
        }
    }

    override suspend fun deleteTimetablesBySubjectId(id: Int) =
        timetableDao.deleteTimetablesBySubjectId(id)

    override suspend fun setSemesterTime(start: String, end: String, firstWeekType: String) {
        try {
            dataStore.edit { settings ->
                settings[START_TIME] = start
                settings[END_TIME] = end
                settings[FIRST_WEEK_TYPE] = firstWeekType
            }
        } catch (e: Exception) {
            Log.e("TimetableRepository", "Error setting semester time", e)
        }
    }

    override suspend fun getDataStoreData(): Flow<Preferences> =
        dataStore.data

}