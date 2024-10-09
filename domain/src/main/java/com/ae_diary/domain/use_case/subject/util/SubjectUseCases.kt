package com.ae_diary.domain.use_case.subject.util

import com.ae_diary.domain.use_case.subject.DeleteSubjectUseCase
import com.ae_diary.domain.use_case.subject.GetAllSubjectsUseCase
import com.ae_diary.domain.use_case.subject.UpsertSubjectUseCase

sealed interface SubjectUseCases {
    val GetAllSubjectsUseCase: GetAllSubjectsUseCase
    val UpsertSubjectUseCase: UpsertSubjectUseCase
    val DeleteSubjectUseCase: DeleteSubjectUseCase
}