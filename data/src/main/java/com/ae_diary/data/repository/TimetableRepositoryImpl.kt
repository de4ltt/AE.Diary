package com.ae_diary.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.ae_diary.data.mapper.toDomain
import com.ae_diary.data.mapper.toEntity
import com.ae_diary.data.model.Timetables
import com.ae_diary.data.model.util.DataStorePreferenceKeys.END_TIME
import com.ae_diary.data.model.util.DataStorePreferenceKeys.FIRST_WEEK_TYPE
import com.ae_diary.data.model.util.DataStorePreferenceKeys.HOLIDAYS
import com.ae_diary.data.model.util.DataStorePreferenceKeys.START_TIME
import com.ae_diary.data.repository.database.dao.TimetablesDAO
import com.ae_diary.domain.model.TimetableDomain
import com.ae_diary.domain.repository.TimetableRepository
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