package com.example.deathnote.data.repository

import com.example.deathnote.data.mapper.toDomain
import com.example.deathnote.data.mapper.toEntity
import com.example.deathnote.data.repository.database.dao.SubjectsDAO
import com.example.deathnote.domain.model.SubjectDomain
import com.example.deathnote.domain.repository.SubjectRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubjectRepositoryImpl @Inject constructor(
    private val subjectDao: SubjectsDAO
): SubjectRepository {

    override suspend fun getAllSubjects(): Flow<List<SubjectDomain>> =
        subjectDao.getAllSubjects().toDomain()

    override suspend fun getSubjectById(id: Int): SubjectDomain =
        subjectDao.getSubjectById(id).toDomain()

    override suspend fun upsertSubject(subject: SubjectDomain) =
        subjectDao.upsertSubject(subject.toEntity())

    override suspend fun deleteSubject(subject: SubjectDomain) =
        subjectDao.deleteSubject(subject.toEntity())


}