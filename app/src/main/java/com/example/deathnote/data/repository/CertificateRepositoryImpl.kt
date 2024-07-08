package com.example.deathnote.data.repository

import com.example.deathnote.data.mapper.toDomain
import com.example.deathnote.data.mapper.toEntity
import com.example.deathnote.data.model.Certificates
import com.example.deathnote.data.repository.database.dao.CertificatesDAO
import com.example.deathnote.domain.model.CertificateDomain
import com.example.deathnote.domain.repository.CertificateRepository
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

    override suspend fun deleteCertificatesByStudentId(id: Int) =
        certificateDao.deleteCertificatesByStudentId(id)

}