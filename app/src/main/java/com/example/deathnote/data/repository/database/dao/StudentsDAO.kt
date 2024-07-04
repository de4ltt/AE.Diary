package com.example.deathnote.data.repository.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.deathnote.data.model.Students
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentsDAO {

    @Query("SELECT * FROM students")
    fun getAllStudents(): Flow<List<Students>>

    @Query("SELECT * FROM students WHERE id = :id")
    fun getStudentById(id: Int): Students

    @Upsert
    fun upsertStudent(student: Students)

    @Delete
    fun deleteStudent(student: Students)

}