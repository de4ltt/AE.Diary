package com.example.deathnote.domain.use_case.timetable

import com.example.deathnote.domain.repository.SubjectRepository
import javax.inject.Inject

class GetSubjectsByIdsUseCase @Inject constructor(
    private val subjectRepository: SubjectRepository
)