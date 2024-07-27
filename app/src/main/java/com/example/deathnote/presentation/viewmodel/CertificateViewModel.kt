package com.example.deathnote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deathnote.domain.model.CertificateDomain
import com.example.deathnote.domain.use_case.certificate.util.CertificateUseCases
import com.example.deathnote.presentation.mapper.toDomain
import com.example.deathnote.presentation.mapper.toPresentation
import com.example.deathnote.presentation.model.Certificate
import com.example.deathnote.presentation.model.event.CertificateUIEvent
import com.example.deathnote.presentation.model.state.CertificateUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
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
                    end = event.endDate
                )
            }

        is CertificateUIEvent.ChangeStartDate ->
            viewModelScope.launch(Dispatchers.IO) {
                _certificateUIState.value = _certificateUIState.value.copy(
                    start = event.startDate
                )
            }

        is CertificateUIEvent.ChangeStudent ->
            viewModelScope.launch(Dispatchers.IO) {
                _certificateUIState.value = _certificateUIState.value.copy(
                    student = event.student
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
    }

    private fun addCertificate(certificate: Certificate) =
        viewModelScope.launch(Dispatchers.IO) {
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

            _certificateUIState.value.apply {
                var curDate = LocalDate.parse(start, formatter)
                val endDate = LocalDate.parse(end, formatter)

                while (curDate <= endDate) {
                    certificateUseCases.AddStudentAbsenceByDateUseCase(
                        curDate.format(formatter),
                        student.id
                    )

                    curDate = curDate.plusDays(1)
                }

                certificateUseCases.AddCertificateUseCase(certificate.toDomain())
            }
        }

    private fun deleteCertificate(certificate: Certificate) =
        viewModelScope.launch(Dispatchers.IO) {
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

            var curDate = LocalDate.parse(certificate.start, formatter)
            val endDate = LocalDate.parse(certificate.end, formatter)

            while (curDate <= endDate) {
                certificateUseCases.DeleteStudentAbsenceByDateUseCase(
                    curDate.format(formatter),
                    certificate.studentId
                )

                curDate = curDate.plusDays(1)
            }

            certificateUseCases.DeleteCertificateUseCase(certificate.toDomain())
        }

    init {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

        viewModelScope.launch(Dispatchers.IO) {
            certificateUseCases.GetAllCertificatesUseCase().collect {
                _allCertificates.value = it.toPresentation(CertificateDomain::toPresentation)

                _orderedCertificates.value = _allCertificates.value.groupBy { item ->
                    (LocalDate.parse(item.start, formatter).month + LocalDate.parse(
                        item.start,
                        formatter
                    ).year.toLong()).toString()
                }
            }
        }
    }
}