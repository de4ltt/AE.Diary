package com.example.deathnote.data.repository.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.deathnote.data.model.SubjectsDismissed
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectsDismissedDAO {

    @Query("SELECT * FROM subjectsdismissed WHERE day = :date")
    fun getAllDaySubjectsDismissed(date: String): Flow<List<SubjectsDismissed>>

    @Upsert
    fun upsertDismissedSubject(subject: SubjectsDismissed)

    @Delete
    fun deleteDismissedSubject(subject: SubjectsDismissed)
}