package com.ae_diary.domain.use_case.subject.util

import com.ae_diary.domain.use_case.subject.DeleteSubjectUseCase
import com.ae_diary.domain.use_case.subject.GetAllSubjectsUseCase
import com.ae_diary.domain.use_case.subject.UpsertSubjectUseCase
import javax.inject.Inject

data class SubjectUseCasesImpl @Inject constructor(
    override val GetAllSubjectsUseCase: GetAllSubjectsUseCase,
    override val UpsertSubjectUseCase: UpsertSubjectUseCase,
    override val DeleteSubjectUseCase: DeleteSubjectUseCase
): SubjectUseCases
