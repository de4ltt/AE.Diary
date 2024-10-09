package com.ae_diary.domain.use_case.certificate.util

import com.ae_diary.domain.use_case.certificate.AddCertificateUseCase
import com.ae_diary.domain.use_case.certificate.AddStudentAbsenceByDateUseCase
import com.ae_diary.domain.use_case.certificate.DeleteCertificateUseCase
import com.ae_diary.domain.use_case.certificate.DeleteStudentAbsenceByDateUseCase
import com.ae_diary.domain.use_case.certificate.GetAllCertificatesUseCase

sealed interface CertificateUseCases {
    val DeleteStudentAbsenceByDateUseCase: DeleteStudentAbsenceByDateUseCase
    val AddStudentAbsenceByDateUseCase: AddStudentAbsenceByDateUseCase
    val GetAllCertificatesUseCase: GetAllCertificatesUseCase
    val AddCertificateUseCase: AddCertificateUseCase
    val DeleteCertificateUseCase: DeleteCertificateUseCase
}