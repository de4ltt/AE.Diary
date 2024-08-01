package com.example.deathnote.domain.use_case.subject

import com.example.deathnote.domain.model.SubjectDomain
import com.example.deathnote.domain.repository.SubjectRepository
import javax.inject.Inject

class DeleteSubjectUseCase @Inject constructor(
    private val subjectRepository: SubjectRepository
) {

    suspend operator fun invoke(subject: SubjectDomain) =
        subjectRepository.deleteSubject(subject)
}