package com.example.ae_diary.domain.repository

import com.example.ae_diary.domain.model.CertificateDomain
import kotlinx.coroutines.flow.Flow

interface CertificateRepository {

    suspend fun getAllCertificates(): Flow<List<CertificateDomain>>

    suspend fun addCertificate(certificate: CertificateDomain)

    suspend fun deleteCertificate(certificate: CertificateDomain)

}