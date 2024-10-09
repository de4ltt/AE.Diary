package com.ae_diary.domain.use_case.subject

import com.ae_diary.domain.repository.SubjectRepository
import javax.inject.Inject

class GetAllSubjectsUseCase @Inject constructor(
    private val subjectRepository: SubjectRepository
) {
    suspend operator fun invoke() = subjectRepository.getAllSubjects()
}