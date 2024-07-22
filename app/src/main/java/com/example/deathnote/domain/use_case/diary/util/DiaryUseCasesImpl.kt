package com.example.deathnote.domain.use_case.diary.util

import com.example.deathnote.domain.use_case.diary.AddStudentAbsenceUseCase
import com.example.deathnote.domain.use_case.diary.AddStudentRespectfulAbsenceUseCase
import com.example.deathnote.domain.use_case.diary.DeleteStudentAbsenceUseCase
import javax.inject.Inject

data class DiaryUseCasesImpl @Inject constructor(
    override val AddStudentAbsenceUseCase: AddStudentAbsenceUseCase,
    override val AddStudentRespectfulAbsenceUseCase: AddStudentRespectfulAbsenceUseCase,
    override val DeleteStudentAbsenceUseCase: DeleteStudentAbsenceUseCase,
): DiaryUseCases
