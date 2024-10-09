package com.ae_diary.presentation.model.state

import com.ae_diary.presentation.model.Student
import com.ae_diary.presentation.model.enums.CertificateDatePickerState
import com.ae_diary.presentation.util.TimeFormatter.nowDateFormatted

data class CertificateUIState(
    val isBottomSheetShown: Boolean = false,
    val isSelectStudentSheetShown: Boolean = false,
    val bottomSheetDatePickerState: CertificateDatePickerState = CertificateDatePickerState.NONE,

    val curStudent: Student = Student(),
    val startDate: String = nowDateFormatted,
    val endDate: String = nowDateFormatted
)