package com.example.deathnote.domain.use_case.certificate

import com.example.deathnote.domain.repository.CertificateRepository
import javax.inject.Inject

class GetAllCertificatesUseCase @Inject constructor(
    private val certificateRepository: CertificateRepository
) {

    suspend operator fun invoke() = certificateRepository.getAllCertificates()

}