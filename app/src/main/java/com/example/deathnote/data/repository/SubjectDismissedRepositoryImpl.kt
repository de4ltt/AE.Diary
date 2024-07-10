package com.example.deathnote.data.repository

import com.example.deathnote.data.mapper.toDomain
import com.example.deathnote.data.mapper.toEntity
import com.example.deathnote.data.model.SubjectsDismissed
import com.example.deathnote.data.repository.database.dao.SubjectsDismissedDAO
import com.example.deathnote.domain.model.SubjectDismissedDomain
import com.example.deathnote.domain.repository.SubjectDismissedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubjectDismissedRepositoryImpl @Inject constructor(
    private val subjectsDismissedDAO: SubjectsDismissedDAO
): SubjectDismissedRepository {

    override suspend fun getAllDaySubjectsDismissed(date: String): Flow<List<SubjectDismissedDomain>> =
        subjectsDismissedDAO.getAllDaySubjectsDismissed(date).toDomain(SubjectsDismissed::toDomain)

    override suspend fun upsertDismissedSubject(subject: SubjectDismissedDomain) =
        subjectsDismissedDAO.upsertDismissedSubject(subject.toEntity())

    override suspend fun deleteDismissedSubject(subject: SubjectDismissedDomain) =
        subjectsDismissedDAO.deleteDismissedSubject(subject.toEntity())
}