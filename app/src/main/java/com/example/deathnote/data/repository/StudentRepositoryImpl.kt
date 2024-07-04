package com.example.deathnote.data.repository

import com.example.deathnote.data.mapper.toDomain
import com.example.deathnote.data.mapper.toEntity
import com.example.deathnote.data.repository.database.dao.StudentsDAO
import com.example.deathnote.domain.model.StudentDomain
import com.example.deathnote.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    private val studentDAO: StudentsDAO
): StudentRepository {
    override suspend fun getAllStudents(): Flow<List<StudentDomain>> =
        studentDAO.getAllStudents().toDomain()

    override suspend fun getStudentById(id: Int): StudentDomain =
        studentDAO.getStudentById(id).toDomain()

    override suspend fun upsertStudent(student: StudentDomain) =
        studentDAO.upsertStudent(student.toEntity())

    override suspend fun deleteStudent(student: StudentDomain) =
        studentDAO.deleteStudent(student.toEntity())
}