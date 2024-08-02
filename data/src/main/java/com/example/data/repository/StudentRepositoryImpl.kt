package com.example.ae_diary.data.repository

import com.example.ae_diary.data.mapper.toDomain
import com.example.ae_diary.data.mapper.toEntity
import com.example.ae_diary.data.model.Students
import com.example.ae_diary.data.repository.database.dao.StudentsDAO
import com.example.ae_diary.domain.model.StudentDomain
import com.example.ae_diary.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    private val studentDAO: StudentsDAO
): StudentRepository {

    override suspend fun getAllStudents(): Flow<List<StudentDomain>> =
        studentDAO.getAllStudents().toDomain(Students::toDomain)

    override suspend fun upsertStudent(student: StudentDomain) =
        studentDAO.upsertStudent(student.toEntity())

    override suspend fun deleteStudent(student: StudentDomain) =
        studentDAO.deleteStudent(student.toEntity())
}