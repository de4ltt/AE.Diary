package com.example.deathnote.domain.use_case.certificate.util

import com.example.deathnote.domain.use_case.certificate.AddCertificateUseCase
import com.example.deathnote.domain.use_case.certificate.DeleteCertificateUseCase
import com.example.deathnote.domain.use_case.certificate.DeleteCertificatesByStudentIdUseCase
import com.example.deathnote.domain.use_case.certificate.GetAllCertificatesUseCase
import javax.inject.Inject

data class CertificateUseCasesImpl @Inject constructor(
    override val GetAllCertificatesUseCase: GetAllCertificatesUseCase,
    override val AddCertificateUseCase: AddCertificateUseCase,
    override val DeleteCertificateUseCase: DeleteCertificateUseCase,
    override val DeleteCertificatesByStudentIdUseCase: DeleteCertificatesByStudentIdUseCase
): CertificateUseCases