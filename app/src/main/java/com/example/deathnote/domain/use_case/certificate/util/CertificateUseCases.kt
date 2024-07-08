package com.example.deathnote.domain.use_case.certificate.util

import com.example.deathnote.domain.use_case.certificate.AddCertificateUseCase
import com.example.deathnote.domain.use_case.certificate.DeleteCertificateUseCase
import com.example.deathnote.domain.use_case.certificate.GetAllCertificatesUseCase

sealed interface CertificateUseCases {

    val GetAllCertificatesUseCase: GetAllCertificatesUseCase
    val AddCertificateUseCase: AddCertificateUseCase
    val DeleteCertificateUseCase: DeleteCertificateUseCase
}