package com.example.deathnote.data.repository.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.deathnote.data.model.Holidays
import kotlinx.coroutines.flow.Flow

@Dao
interface HolidaysDAO {

    @Query("SELECT * FROM holidays")
    fun getAllHolidays(): Flow<List<Holidays>>

    @Upsert
    fun addHoliday(holiday: Holidays)

    @Delete
    fun deleteHoliday(holiday: Holidays)
}