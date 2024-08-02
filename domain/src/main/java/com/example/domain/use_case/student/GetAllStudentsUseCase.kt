package com.example.ae_diary.domain.use_case.student

import com.example.ae_diary.domain.repository.StudentRepository
import javax.inject.Inject

class GetAllStudentsUseCase @Inject constructor(
    private val studentRepository: StudentRepository
) {

    suspend operator fun invoke() = studentRepository.getAllStudents()
}