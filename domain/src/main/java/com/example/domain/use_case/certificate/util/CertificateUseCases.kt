package com.example.deathnote.domain.use_case.certificate.util

import com.example.deathnote.domain.use_case.certificate.AddCertificateUseCase
import com.example.deathnote.domain.use_case.certificate.AddStudentAbsenceByDateUseCase
import com.example.deathnote.domain.use_case.certificate.DeleteCertificateUseCase
import com.example.deathnote.domain.use_case.certificate.DeleteStudentAbsenceByDateUseCase
import com.example.deathnote.domain.use_case.certificate.GetAllCertificatesUseCase

sealed interface CertificateUseCases {
    val DeleteStudentAbsenceByDateUseCase: DeleteStudentAbsenceByDateUseCase
    val AddStudentAbsenceByDateUseCase: AddStudentAbsenceByDateUseCase
    val GetAllCertificatesUseCase: GetAllCertificatesUseCase
    val AddCertificateUseCase: AddCertificateUseCase
    val DeleteCertificateUseCase: DeleteCertificateUseCase
}