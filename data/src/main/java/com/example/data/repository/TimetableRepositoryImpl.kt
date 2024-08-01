package com.example.deathnote.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.data.model.util.DataStorePreferenceKeys.END_TIME
import com.example.data.model.util.DataStorePreferenceKeys.FIRST_WEEK_TYPE
import com.example.data.model.util.DataStorePreferenceKeys.HOLIDAYS
import com.example.data.model.util.DataStorePreferenceKeys.START_TIME
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

    override suspend fun upsertTimetable(timetable: TimetableDomain) =
        timetableDao.upsertTimetable(timetable.toEntity())

    override suspend fun deleteAllTimetables() =
        timetableDao.deleteAllTimetables()

    override suspend fun deleteTimetable(timetable: TimetableDomain) {
        timetable.apply {
            timetableDao.deleteTimetable(
                date = date,
                subjectId = subjectId
            )
        }
    }

    override suspend fun setSemesterTime(
        start: String,
        end: String,
        firstWeekType: String,
        holidays: String
    ) {
        dataStore.edit { settings ->
            settings[START_TIME] = start
            settings[END_TIME] = end
            settings[FIRST_WEEK_TYPE] = firstWeekType
            settings[HOLIDAYS] = holidays
        }
    }

    override suspend fun getDataStoreData(): Flow<Preferences> =
        dataStore.data

}