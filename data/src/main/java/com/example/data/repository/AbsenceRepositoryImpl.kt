package com.example.ae_diary.data.repository

import com.example.ae_diary.data.mapper.toDomain
import com.example.ae_diary.data.mapper.toEntity
import com.example.ae_diary.data.model.Absences
import com.example.ae_diary.data.repository.database.dao.AbsencesDAO
import com.example.ae_diary.domain.model.AbsenceDomain
import com.example.ae_diary.domain.repository.AbsenceRepository
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

    override suspend fun addStudentAbsenceByDate(date: String, studentId: Int) =
        absencesDAO.addStudentAbsenceByDate(date, studentId)

    override suspend fun deleteStudentAbsenceByDate(date: String, studentId: Int) =
        absencesDAO.deleteStudentAbsenceByDate(date, studentId)

}