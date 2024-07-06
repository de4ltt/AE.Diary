package com.example.deathnote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deathnote.domain.use_case.subject.util.SubjectUseCases
import com.example.deathnote.presentation.mapper.toDomain
import com.example.deathnote.presentation.mapper.toPresentation
import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.event.SubjectUIEvent
import com.example.deathnote.presentation.model.state.SubjectDialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectViewModel @Inject constructor(
    private val subjectUseCases: SubjectUseCases
) : ViewModel() {

    private val _allSubjects: MutableStateFlow<List<Subject>> = MutableStateFlow(emptyList())
    val allSubjects: StateFlow<List<Subject>> = _allSubjects.asStateFlow()

    private val _subjectDialogState: MutableStateFlow<SubjectDialogState> =
        MutableStateFlow(SubjectDialogState())
    val subjectDialogState: StateFlow<SubjectDialogState> = _subjectDialogState.asStateFlow()

    private val retrievedSubject: MutableStateFlow<Subject?> = MutableStateFlow(null)

    fun onEvent(event: SubjectUIEvent) {
        when (event) {
            is SubjectUIEvent.ChangeDialogContent ->
                viewModelScope.launch(Dispatchers.IO) {
                    _subjectDialogState.value = _subjectDialogState.value.copy()
                }

            is SubjectUIEvent.ChangeDialogTitle ->
                viewModelScope.launch(Dispatchers.IO) {
                    _subjectDialogState.value =
                        _subjectDialogState.value.copy(title = event.title)
                }

            is SubjectUIEvent.ChangeDialogState ->
                viewModelScope.launch(Dispatchers.IO) {
                    _subjectDialogState.value =
                        _subjectDialogState.value.copy(isShown = event.state)
                }

            is SubjectUIEvent.ChangeSubjectName ->
                viewModelScope.launch(Dispatchers.IO) {
                    _subjectDialogState.value =
                        _subjectDialogState.value.copy(
                            subject = subjectDialogState.value.subject.copy(name = event.name)
                        )
                }

            SubjectUIEvent.IdleSubject ->
                viewModelScope.launch(Dispatchers.IO) {
                    _subjectDialogState.value =
                        _subjectDialogState.value.copy(subject = Subject())
                }

            is SubjectUIEvent.ChangeSubjectType ->
                viewModelScope.launch(Dispatchers.IO) {
                    _subjectDialogState.value =
                        _subjectDialogState.value.copy(
                            subject = subjectDialogState.value.subject.copy(type = event.type)
                        )
                }

            is SubjectUIEvent.SelectSubject ->
                viewModelScope.launch(Dispatchers.IO) {
                    _subjectDialogState.value =
                        _subjectDialogState.value.copy(subject = event.subject)
                }

            is SubjectUIEvent.DeleteSubject -> deleteSubject(event.subject)

            is SubjectUIEvent.UpsertSubject -> upsertSubject(event.subject)
        }
    }

    fun getSubjectById(id: Int?): Subject? {
        retrieveSubjectById(id)
        return retrievedSubject.value
    }

    private fun retrieveSubjectById(id: Int?) =
        viewModelScope.launch(Dispatchers.IO) {
            if (id == null) null else
                subjectUseCases.GetSubjectByIdUseCase(id).toPresentation()
        }

    private fun deleteSubject(subject: Subject) =
        viewModelScope.launch(Dispatchers.IO) {
            subjectUseCases.DeleteSubjectUseCase(subject.toDomain())
        }

    private fun upsertSubject(subject: Subject) =
        viewModelScope.launch(Dispatchers.IO) {
            subjectUseCases.UpsertSubjectUseCase(subject.toDomain())
        }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            subjectUseCases.GetAllSubjectsUseCase().collect {
                _allSubjects.value = it.toPresentation()
            }
        }
    }
}