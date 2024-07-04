package com.example.deathnote.domain.use_case.student.util

import com.example.deathnote.domain.use_case.student.DeleteStudentUseCase
import com.example.deathnote.domain.use_case.student.GetAllStudentsUseCase
import com.example.deathnote.domain.use_case.student.GetStudentById
import com.example.deathnote.domain.use_case.student.UpsertStudentUseCase

data class StudentUseCaseImpl(
    override val GetAllStudentsUseCase: GetAllStudentsUseCase,
    override val UpsertStudentUseCase: UpsertStudentUseCase,
    override val DeleteStudentUseCase: DeleteStudentUseCase,
    override val GetStudentById: GetStudentById
): StudentUseCases
