package com.example.deathnote.domain.repository

import com.example.deathnote.domain.model.AbsenceDomain
import kotlinx.coroutines.flow.Flow

interface AbsenceRepository {

    suspend fun getAllDayAbsences(date: String): Flow<List<AbsenceDomain>>
    suspend fun upsertAbsence(absence: AbsenceDomain)
    suspend fun deleteAbsence(absence: AbsenceDomain)

}