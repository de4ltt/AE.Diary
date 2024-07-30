package com.example.deathnote.presentation.model.state

import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.interfaces.CertificateDatePickerState
import com.example.deathnote.presentation.util.TimeFormatter.nowDateFormatted

data class CertificateUIState(
    val isBottomSheetShown: Boolean = false,
    val isSelectStudentSheetShown: Boolean = false,
    val bottomSheetDatePickerState: CertificateDatePickerState = CertificateDatePickerState.NONE,

    val curStudent: Student = Student(),
    val startDate: String = nowDateFormatted,
    val endDate: String = nowDateFormatted
)