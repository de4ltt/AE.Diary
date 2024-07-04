package com.example.deathnote.domain.use_case.student

import com.example.deathnote.domain.model.StudentDomain
import com.example.deathnote.domain.repository.StudentRepository
import javax.inject.Inject

class DeleteStudentUseCase @Inject constructor(
    private val studentRepository: StudentRepository
) {

    suspend operator fun invoke(student: StudentDomain) = studentRepository.deleteStudent(student)
}