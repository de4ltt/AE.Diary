package com.example.ae_diary.presentation.model.event

import com.example.ae_diary.presentation.model.Certificate
import com.example.ae_diary.presentation.model.Student
import com.example.ae_diary.presentation.model.enums.CertificateDatePickerState

sealed class CertificateUIEvent{

    data class ChangeStudent(val student: Student): CertificateUIEvent()
    data class ChangeStartDate(val startDate: String): CertificateUIEvent()
    data class ChangeEndDate(val endDate: String): CertificateUIEvent()
    data class ChangeDialogState(val state: Boolean): CertificateUIEvent()

    data class ChangeStudentSheetState(val state: Boolean): CertificateUIEvent()

    data class AddCertificate(val certificate: Certificate): CertificateUIEvent()
    data class DeleteCertificate(val certificate: Certificate): CertificateUIEvent()

    data class ChangeCertificateDatePickerState(val state: CertificateDatePickerState) : CertificateUIEvent()
}