package com.example.ae_diary.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ae_diary.domain.model.CertificateDomain
import com.example.ae_diary.domain.use_case.certificate.util.CertificateUseCases
import com.example.ae_diary.presentation.mapper.toDomain
import com.example.ae_diary.presentation.mapper.toPresentation
import com.example.ae_diary.presentation.model.Certificate
import com.example.ae_diary.presentation.model.event.CertificateUIEvent
import com.example.ae_diary.presentation.model.state.CertificateUIState
import com.example.ae_diary.presentation.util.TimeFormatter.dateFormatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CertificateViewModel @Inject constructor(
    private val certificateUseCases: CertificateUseCases
) : ViewModel() {

    private val _allCertificates: MutableStateFlow<List<Certificate>> =
        MutableStateFlow(emptyList())

    private val _orderedCertificates: MutableStateFlow<Map<String, List<Certificate>>> =
        MutableStateFlow(emptyMap())
    val orderedCertificates = _orderedCertificates.asStateFlow()

    private val _certificateUIState: MutableStateFlow<CertificateUIState> =
        MutableStateFlow(CertificateUIState())
    val certificateUIState = _certificateUIState.asStateFlow()

    fun onEvent(event: CertificateUIEvent) = when (event) {
        is CertificateUIEvent.ChangeDialogState ->
            viewModelScope.launch(Dispatchers.IO) {
                _certificateUIState.value = _certificateUIState.value.copy(
                    isBottomSheetShown = event.state
                )
            }

        is CertificateUIEvent.ChangeEndDate ->
            viewModelScope.launch(Dispatchers.IO) {
                _certificateUIState.value = _certificateUIState.value.copy(
                    endDate = event.endDate
                )
            }

        is CertificateUIEvent.ChangeStartDate ->
            viewModelScope.launch(Dispatchers.IO) {
                _certificateUIState.value = _certificateUIState.value.copy(
                    startDate = event.startDate
                )
            }

        is CertificateUIEvent.ChangeStudent ->
            viewModelScope.launch(Dispatchers.IO) {
                _certificateUIState.value = _certificateUIState.value.copy(
                    curStudent = event.student
                )
            }

        is CertificateUIEvent.ChangeStudentSheetState ->
            viewModelScope.launch(Dispatchers.IO) {
                _certificateUIState.value = _certificateUIState.value.copy(
                    isSelectStudentSheetShown = event.state
                )
            }

        is CertificateUIEvent.AddCertificate -> addCertificate(event.certificate)
        is CertificateUIEvent.DeleteCertificate -> deleteCertificate(event.certificate)

        is CertificateUIEvent.ChangeCertificateDatePickerState ->
            viewModelScope.launch(Dispatchers.IO) {
                _certificateUIState.value = _certificateUIState.value.copy(
                    bottomSheetDatePickerState = event.state
                )
            }
    }

    private fun addCertificate(certificate: Certificate) =
        viewModelScope.launch(Dispatchers.IO) {

            _certificateUIState.value.apply {
                var curDate = LocalDate.parse(startDate, dateFormatter)
                val endDate = LocalDate.parse(endDate, dateFormatter)

                while (curDate <= endDate) {
                    certificateUseCases.AddStudentAbsenceByDateUseCase(
                        curDate.format(dateFormatter),
                        curStudent.id
                    )

                    curDate = curDate.plusDays(1)
                }

                certificateUseCases.AddCertificateUseCase(certificate.toDomain())
            }
        }

    private fun deleteCertificate(certificate: Certificate) =
        viewModelScope.launch(Dispatchers.IO) {

            var curDate = LocalDate.parse(certificate.start, dateFormatter)
            val endDate = LocalDate.parse(certificate.end, dateFormatter)

            while (curDate <= endDate) {
                certificateUseCases.DeleteStudentAbsenceByDateUseCase(
                    curDate.format(dateFormatter),
                    certificate.studentId
                )

                curDate = curDate.plusDays(1)
            }

            certificateUseCases.DeleteCertificateUseCase(certificate.toDomain())
        }

    init {

        viewModelScope.launch(Dispatchers.IO) {
            certificateUseCases.GetAllCertificatesUseCase().collect {
                _allCertificates.value = it.toPresentation(CertificateDomain::toPresentation)

                _orderedCertificates.value = _allCertificates.value.groupBy { item ->
                    (LocalDate.parse(item.start, dateFormatter).month + LocalDate.parse(
                        item.start,
                        dateFormatter
                    ).year.toLong()).toString()
                }
            }
        }
    }
}