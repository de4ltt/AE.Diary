package com.example.deathnote.domain.repository

import com.example.deathnote.domain.model.AbsenceDomain
import kotlinx.coroutines.flow.Flow

interface AbsenceRepository {

    suspend fun getAllAbsence(): Flow<List<AbsenceDomain>>

    suspend fun upsertAbsence(absence: AbsenceDomain)

    suspend fun deleteAbsence(absence: AbsenceDomain)

    suspend fun addStudentAbsenceByDate(date: String, studentId: Int)

    suspend fun deleteStudentAbsenceByDate(date: String, studentId: Int)
}