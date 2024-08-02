package com.example.ae_diary.domain.repository

import com.example.ae_diary.domain.model.SubjectDomain
import kotlinx.coroutines.flow.Flow

interface SubjectRepository {

    suspend fun getAllSubjects(): Flow<List<SubjectDomain>>

    suspend fun upsertSubject(subject: SubjectDomain)

    suspend fun deleteSubject(subject: SubjectDomain)
}