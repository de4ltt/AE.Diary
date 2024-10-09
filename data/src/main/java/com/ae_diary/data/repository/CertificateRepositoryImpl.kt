package com.ae_diary.data.repository

import com.ae_diary.data.mapper.toDomain
import com.ae_diary.data.mapper.toEntity
import com.ae_diary.data.model.Certificates
import com.ae_diary.data.repository.database.dao.CertificatesDAO
import com.ae_diary.domain.model.CertificateDomain
import com.ae_diary.domain.repository.CertificateRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CertificateRepositoryImpl @Inject constructor(
    private val certificateDao: CertificatesDAO
): CertificateRepository {

    override suspend fun getAllCertificates(): Flow<List<CertificateDomain>> =
        certificateDao.getAllCertificates().toDomain(Certificates::toDomain)

    override suspend fun addCertificate(certificate: CertificateDomain) =
        certificateDao.addCertificate(certificate.toEntity())

    override suspend fun deleteCertificate(certificate: CertificateDomain) =
        certificateDao.deleteCertificate(certificate.toEntity())

}