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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CertificateViewModel @Inject constructor(
    private val certificateUseCases: CertificateUseCases
) : ViewModel() {

    private val _allCertificates: MutableStateFlow<List<Certificate>> =
        MutableStateFlow(emptyList())
    val allCertificates = _allCertificates.asStateFlow()

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

        is CertificateUIEvent.AddCertificate -> addCertificate(event.certificate)
        is CertificateUIEvent.DeleteCertificate -> deleteCertificate(event.certificate)
    }

    private fun addCertificate(certificate: Certificate) =
        viewModelScope.launch(Dispatchers.IO) {
            certificateUseCases.AddCertificateUseCase(certificate.toDomain())
        }

    private fun deleteCertificate(certificate: Certificate) =
        viewModelScope.launch(Dispatchers.IO) {
            certificateUseCases.DeleteCertificateUseCase(certificate.toDomain())
        }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            certificateUseCases.GetAllCertificatesUseCase().collect {
                _allCertificates.value = it.toPresentation(CertificateDomain::toPresentation)
            }
        }
    }
}