package com.example.deathnote.domain.use_case.student.util

import com.example.deathnote.domain.use_case.student.DeleteStudentUseCase
import com.example.deathnote.domain.use_case.student.GetAllStudentsUseCase
import com.example.deathnote.domain.use_case.student.UpsertStudentUseCase
import javax.inject.Inject

data class StudentUseCasesImpl @Inject constructor(
    override val GetAllStudentsUseCase: GetAllStudentsUseCase,
    override val UpsertStudentUseCase: UpsertStudentUseCase,
    override val DeleteStudentUseCase: DeleteStudentUseCase,
): StudentUseCases
