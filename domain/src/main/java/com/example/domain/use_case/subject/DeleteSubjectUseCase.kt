package com.example.ae_diary.domain.use_case.subject

import com.example.ae_diary.domain.model.SubjectDomain
import com.example.ae_diary.domain.repository.SubjectRepository
import javax.inject.Inject

class DeleteSubjectUseCase @Inject constructor(
    private val subjectRepository: SubjectRepository
) {

    suspend operator fun invoke(subject: SubjectDomain) =
        subjectRepository.deleteSubject(subject)
}