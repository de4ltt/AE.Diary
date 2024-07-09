package com.example.deathnote.domain.repository

import com.example.deathnote.domain.model.StudentDomain
import kotlinx.coroutines.flow.Flow

interface StudentRepository {

    suspend fun getAllStudents(): Flow<List<StudentDomain>>

    suspend fun getStudentById(id: Int): Flow<StudentDomain>

    suspend fun upsertStudent(student: StudentDomain)

    suspend fun deleteStudent(student: StudentDomain)

}