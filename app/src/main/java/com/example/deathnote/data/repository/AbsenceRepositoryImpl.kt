package com.example.deathnote.data.repository

import com.example.deathnote.data.mapper.toDomain
import com.example.deathnote.data.mapper.toEntity
import com.example.deathnote.data.model.Absences
import com.example.deathnote.data.repository.database.dao.AbsencesDAO
import com.example.deathnote.domain.model.AbsenceDomain
import com.example.deathnote.domain.repository.AbsenceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AbsenceRepositoryImpl @Inject constructor(
    private val absencesDAO: AbsencesDAO
): AbsenceRepository {

    override suspend fun getAllAbsence(): Flow<List<AbsenceDomain>> =
        absencesDAO.getAllAbsences().toDomain(Absences::toDomain)

    override suspend fun upsertAbsence(absence: AbsenceDomain) =
        absencesDAO.upsertAbsence(absence.toEntity())

    override suspend fun deleteAbsence(absence: AbsenceDomain) =
        absencesDAO.deleteAbsence(absence.toEntity())

}