package com.example.deathnote.domain.use_case.subject.util

import com.example.deathnote.domain.use_case.subject.DeleteSubjectUseCase
import com.example.deathnote.domain.use_case.subject.GetAllSubjectsUseCase
import com.example.deathnote.domain.use_case.subject.GetSubjectByIdUseCase
import com.example.deathnote.domain.use_case.subject.UpsertSubjectUseCase
import javax.inject.Inject

data class SubjectUseCasesImpl @Inject constructor(
    override val GetAllSubjectsUseCase: GetAllSubjectsUseCase,
    override val GetSubjectByIdUseCase: GetSubjectByIdUseCase,
    override val UpsertSubjectUseCase: UpsertSubjectUseCase,
    override val DeleteSubjectUseCase: DeleteSubjectUseCase
): SubjectUseCases
