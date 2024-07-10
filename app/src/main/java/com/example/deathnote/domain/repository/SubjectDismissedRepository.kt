package com.example.deathnote.domain.repository

import com.example.deathnote.domain.model.SubjectDismissedDomain
import kotlinx.coroutines.flow.Flow

interface SubjectDismissedRepository {

    suspend fun getAllDaySubjectsDismissed(date: String): Flow<List<SubjectDismissedDomain>>
    suspend fun upsertDismissedSubject(subject: SubjectDismissedDomain)
    suspend fun deleteDismissedSubject(subject: SubjectDismissedDomain)

}