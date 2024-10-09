package com.ae_diary.domain.use_case.subject

import com.ae_diary.domain.model.SubjectDomain
import com.ae_diary.domain.repository.SubjectRepository
import javax.inject.Inject

class UpsertSubjectUseCase @Inject constructor(
    private val subjectRepository: SubjectRepository
) {

    suspend operator fun invoke(subject: SubjectDomain) =
        subjectRepository.upsertSubject(subject)
}