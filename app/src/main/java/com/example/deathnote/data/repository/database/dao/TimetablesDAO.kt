package com.example.deathnote.data.repository.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.deathnote.data.model.Timetables
import kotlinx.coroutines.flow.Flow

@Dao
interface TimetablesDAO {

    @Query("SELECT * FROM timetables")
    fun getAllTimetables(): Flow<List<Timetables>>

    @Upsert
    fun upsertTimetable(timetable: Timetables)

    @Delete
    fun deleteTimetable(timetable: Timetables)

    @Query("DELETE FROM timetables WHERE subjectId=:id")
    fun deleteTimetablesBySubjectId(id: Int)

    @Query("SELECT subjectId FROM timetables WHERE dayOfWeek=:day")
    fun getTimetablesByDay(day: String): Flow<List<Int>>
}