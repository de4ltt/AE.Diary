package com.example.deathnote.domain.use_case.subject.util

import com.example.deathnote.domain.use_case.subject.DeleteSubjectUseCase
import com.example.deathnote.domain.use_case.subject.GetAllSubjectsUseCase
import com.example.deathnote.domain.use_case.subject.GetSubjectByIdUseCase
import com.example.deathnote.domain.use_case.subject.UpsertSubjectUseCase

sealed interface SubjectUseCases {
    val GetAllSubjectsUseCase: GetAllSubjectsUseCase
    val GetSubjectByIdUseCase: GetSubjectByIdUseCase
    val UpsertSubjectUseCase: UpsertSubjectUseCase
    val DeleteSubjectUseCase: DeleteSubjectUseCase
}