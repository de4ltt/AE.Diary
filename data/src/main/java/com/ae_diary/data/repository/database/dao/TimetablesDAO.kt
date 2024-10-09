package com.ae_diary.data.repository.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.ae_diary.data.model.Timetables
import kotlinx.coroutines.flow.Flow

@Dao
interface TimetablesDAO {

    @Query("SELECT * FROM timetables")
    fun getAllTimetables(): Flow<List<Timetables>>

    @Upsert
    fun upsertTimetable(timetable: Timetables)

    @Query("DELETE FROM timetables")
    fun deleteAllTimetables()

    @Query(
        """
        DELETE FROM timetables 
        WHERE rowid IN (
            SELECT rowid FROM timetables 
            WHERE date = :date AND subjectId = :subjectId 
            LIMIT 1
        )
    """
    )
    fun deleteTimetable(date: String, subjectId: Int)

    @Query("SELECT subjectId FROM timetables WHERE date=:date")
    fun getTimetablesByDay(date: String): Flow<List<Int>>
}