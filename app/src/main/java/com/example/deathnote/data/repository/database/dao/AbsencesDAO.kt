package com.example.deathnote.data.repository.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.deathnote.data.model.Absences
import kotlinx.coroutines.flow.Flow

@Dao
interface AbsencesDAO {

    @Query("SELECT * FROM absences")
    fun getAllAbsences(): Flow<List<Absences>>

    @Upsert
    fun upsertAbsence(absence: Absences)

    @Delete
    fun deleteAbsence(absence: Absences)
}