package com.example.ae_diary.domain.repository

import com.example.ae_diary.domain.model.StudentDomain
import kotlinx.coroutines.flow.Flow

interface StudentRepository {

    suspend fun getAllStudents(): Flow<List<StudentDomain>>

    suspend fun upsertStudent(student: StudentDomain)

    suspend fun deleteStudent(student: StudentDomain)

}