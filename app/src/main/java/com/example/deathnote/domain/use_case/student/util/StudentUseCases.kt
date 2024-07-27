package com.example.deathnote.domain.use_case.student.util

import com.example.deathnote.domain.use_case.student.DeleteStudentUseCase
import com.example.deathnote.domain.use_case.student.GetAllStudentsUseCase
import com.example.deathnote.domain.use_case.student.UpsertStudentUseCase

sealed interface StudentUseCases{
    val GetAllStudentsUseCase: GetAllStudentsUseCase
    val UpsertStudentUseCase: UpsertStudentUseCase
    val DeleteStudentUseCase: DeleteStudentUseCase
}