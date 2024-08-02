package com.example.ae_diary.presentation.ui.screen.main_screen.components.certificates_screen_ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ae_diary.R
import com.example.ae_diary.presentation.model.Certificate
import com.example.ae_diary.presentation.model.enums.CertificateDatePickerState
import com.example.ae_diary.presentation.model.event.CertificateUIEvent
import com.example.ae_diary.presentation.model.state.CertificateUIState
import com.example.ae_diary.presentation.ui.cross_screen_ui.bottom_sheet.BottomBarWithTextFields

@Composable
fun AddCertificateBottomSheet(
    isStudentFieldActive: Boolean = true,
    studentNavigate: () -> Unit,
    onEvent: (CertificateUIEvent) -> Unit,
    state: CertificateUIState
) {

    state.apply {
        if (isBottomSheetShown)
            BottomBarWithTextFields(
                title = R.string.add_certificate,
                isActive = curStudent.name != "",
                onAcceptRequest = {
                    onEvent(
                        CertificateUIEvent.AddCertificate(
                            Certificate(
                                studentId = curStudent.id,
                                start = startDate,
                                end = endDate
                            )
                        )
                    )
                    onEvent(CertificateUIEvent.ChangeDialogState(false))
                },
                onDismissRequest = {
                    onEvent(CertificateUIEvent.ChangeDialogState(false))
                },
                content = {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        SelectStudentField(
                            isActive = isStudentFieldActive,
                            student = curStudent,
                            onEvent = onEvent,
                            studentNavigate = studentNavigate
                        )

                        LazyVerticalGrid(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            columns = GridCells.Fixed(2),
                            horizontalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            item {
                                CertificatesDatePickerField(
                                    title = R.string.start_date,
                                    value = startDate,
                                    onClick = {
                                        onEvent(
                                            CertificateUIEvent.ChangeCertificateDatePickerState(
                                                CertificateDatePickerState.START
                                            )
                                        )
                                    }
                                )
                            }

                            item {
                                CertificatesDatePickerField(
                                    title = R.string.end_date,
                                    value = endDate,
                                    onClick = {
                                        onEvent(
                                            CertificateUIEvent.ChangeCertificateDatePickerState(
                                                CertificateDatePickerState.END
                                            )
                                        )
                                    }
                                )
                            }

                        }
                    }

                }
            )
    }
}