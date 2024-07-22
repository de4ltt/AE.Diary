package com.example.deathnote.domain.use_case.diary.util

import com.example.deathnote.domain.use_case.diary.AddStudentAbsenceUseCase
import com.example.deathnote.domain.use_case.diary.AddStudentRespectfulAbsenceUseCase
import com.example.deathnote.domain.use_case.diary.DeleteStudentAbsenceUseCase

sealed interface DiaryUseCases {
    val AddStudentAbsenceUseCase: AddStudentAbsenceUseCase
    val AddStudentRespectfulAbsenceUseCase: AddStudentRespectfulAbsenceUseCase
    val DeleteStudentAbsenceUseCase: DeleteStudentAbsenceUseCase
}