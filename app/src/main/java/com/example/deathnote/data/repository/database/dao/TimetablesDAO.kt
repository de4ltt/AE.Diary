package com.example.deathnote.data.repository.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.deathnote.data.model.Timetables
import kotlinx.coroutines.flow.Flow

@Dao
interface TimetablesDAO {

    @Query("SELECT * FROM Timetables")
    fun getAllTimetables(): Flow<List<Timetables>>

    @Upsert
    fun upsertTimetable(timetable: Timetables)
}