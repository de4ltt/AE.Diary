package com.example.ae_diary.data.repository.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.ae_diary.data.model.Absences
import kotlinx.coroutines.flow.Flow

@Dao
interface AbsencesDAO {

    @Query("SELECT * FROM absences")
    fun getAllAbsences(): Flow<List<Absences>>

    @Query(
        """
        UPDATE Absences SET respectful = 1
        WHERE studentId = :studentId AND timetableId IN (
            SELECT id FROM Timetables WHERE date = :date
        )
        """
    )
    suspend fun addStudentAbsenceByDate(date: String, studentId: Int)

    @Query(
        """
        UPDATE Absences SET respectful = 0
        WHERE studentId = :studentId AND timetableId IN (
            SELECT id FROM Timetables WHERE date = :date
        )
        """
    )
    suspend fun deleteStudentAbsenceByDate(date: String, studentId: Int)

    @Upsert
    fun upsertAbsence(absence: Absences)

    @Delete
    fun deleteAbsence(absence: Absences)
}