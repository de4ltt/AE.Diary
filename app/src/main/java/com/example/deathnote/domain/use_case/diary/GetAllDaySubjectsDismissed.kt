package com.example.deathnote.domain.use_case.diary

import com.example.deathnote.domain.repository.SubjectDismissedRepository
import javax.inject.Inject

class GetAllDaySubjectsDismissed @Inject constructor(
    private val subjectDismissedRepository: SubjectDismissedRepository
) {

    suspend operator fun invoke(date: String) =
        subjectDismissedRepository.getAllDaySubjectsDismissed(date)
}
