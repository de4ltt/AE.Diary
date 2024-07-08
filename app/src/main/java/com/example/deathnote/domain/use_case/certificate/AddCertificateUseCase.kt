package com.example.deathnote.domain.use_case.certificate

import com.example.deathnote.domain.model.CertificateDomain
import com.example.deathnote.domain.repository.CertificateRepository
import javax.inject.Inject

class AddCertificateUseCase @Inject constructor(
    private val certificateRepository: CertificateRepository
) {

    suspend operator fun invoke(certificate: CertificateDomain) =
        certificateRepository.addCertificate(certificate)
}