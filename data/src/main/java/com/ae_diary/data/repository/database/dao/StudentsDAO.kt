package com.ae_diary.data.repository.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.ae_diary.data.model.Students
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentsDAO {

    @Query("SELECT * FROM students")
    fun getAllStudents(): Flow<List<Students>>

    @Upsert
    fun upsertStudent(student: Students)

    @Delete
    fun deleteStudent(student: Students)

}