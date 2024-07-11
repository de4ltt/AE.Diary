package com.example.deathnote.data.repository.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.deathnote.data.model.WeekTypes
import kotlinx.coroutines.flow.Flow

@Dao
interface WeekTypesDAO {

    @Query("SELECT * FROM weektypes")
    fun getAllWeekTypes(): Flow<List<WeekTypes>>

    @Upsert
    fun upsertWeekType(weekType: WeekTypes)

    @Query("DELETE FROM weektypes")
    fun deleteAllWeekType()

    @Query("SELECT type FROM weektypes WHERE day = :day")
    suspend fun getWeekTypeByDay(day: String): String
}