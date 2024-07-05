package com.example.deathnote.domain.use_case.subject

import com.example.deathnote.domain.repository.SubjectRepository
import javax.inject.Inject

class GetSubjectByIdUseCase @Inject constructor(
    private val subjectRepository: SubjectRepository
) {

    suspend operator fun invoke(id: Int) = subjectRepository.getSubjectById(id)

}