package com.ae_diary.domain.use_case.certificate.util

import com.ae_diary.domain.use_case.certificate.AddCertificateUseCase
import com.ae_diary.domain.use_case.certificate.AddStudentAbsenceByDateUseCase
import com.ae_diary.domain.use_case.certificate.DeleteCertificateUseCase
import com.ae_diary.domain.use_case.certificate.DeleteStudentAbsenceByDateUseCase
import com.ae_diary.domain.use_case.certificate.GetAllCertificatesUseCase
import javax.inject.Inject

data class CertificateUseCasesImpl @Inject constructor(
    override val GetAllCertificatesUseCase: GetAllCertificatesUseCase,
    override val AddCertificateUseCase: AddCertificateUseCase,
    override val DeleteCertificateUseCase: DeleteCertificateUseCase,
    override val AddStudentAbsenceByDateUseCase: AddStudentAbsenceByDateUseCase,
    override val DeleteStudentAbsenceByDateUseCase: DeleteStudentAbsenceByDateUseCase,
): CertificateUseCases