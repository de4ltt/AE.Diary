package com.example.deathnote.domain.use_case.student

import com.example.deathnote.domain.repository.StudentRepository
import javax.inject.Inject

class GetAllStudentsUseCase @Inject constructor(
    private val studentRepository: StudentRepository
) {

    suspend operator fun invoke() = studentRepository.getAllStudents()
}