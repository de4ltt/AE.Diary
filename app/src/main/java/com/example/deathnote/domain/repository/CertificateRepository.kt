package com.example.deathnote.domain.repository

import com.example.deathnote.domain.model.CertificateDomain
import kotlinx.coroutines.flow.Flow

interface CertificateRepository {

    suspend fun getAllCertificates(): Flow<List<CertificateDomain>>

    suspend fun addCertificate(certificate: CertificateDomain)

    suspend fun deleteCertificate(certificate: CertificateDomain)

    suspend fun deleteCertificatesByStudentId(id: Int)
}