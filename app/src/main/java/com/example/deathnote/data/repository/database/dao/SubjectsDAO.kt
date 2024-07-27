package com.example.deathnote.data.repository.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.deathnote.data.model.Subjects
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectsDAO {

    @Query("SELECT * FROM subjects")
    fun getAllSubjects(): Flow<List<Subjects>>

    @Upsert
    fun upsertSubject(subject: Subjects)

    @Delete
    fun deleteSubject(subject: Subjects)

}