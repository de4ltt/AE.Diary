package com.example.deathnote.domain.use_case.diary

import com.example.deathnote.domain.model.SubjectDismissedDomain
import com.example.deathnote.domain.repository.SubjectDismissedRepository
import javax.inject.Inject

class DeleteDismissedSubjectUseCase @Inject constructor(
    private val subjectDismissedRepository: SubjectDismissedRepository
) {

    suspend operator fun invoke(subject: SubjectDismissedDomain) =
        subjectDismissedRepository.deleteDismissedSubject(subject)

}