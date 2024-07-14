package com.example.deathnote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deathnote.domain.model.StudentDomain
import com.example.deathnote.domain.model.SubjectDomain
import com.example.deathnote.domain.use_case.student.util.StudentUseCases
import com.example.deathnote.domain.use_case.subject.util.SubjectUseCases
import com.example.deathnote.presentation.mapper.toPresentation
import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.event.StatisticsUIEvent
import com.example.deathnote.presentation.model.state.StatisticsUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val subjectUseCases: SubjectUseCases,
    private val studentUseCases: StudentUseCases
) : ViewModel() {

    private val _allSubjects = MutableStateFlow<List<Subject>>(emptyList())
    val allSubjects = _allSubjects.asStateFlow()

    private val _allStudents = MutableStateFlow<List<Student>>(emptyList())
    val allStudents = _allStudents.asStateFlow()

    private val _statisticsUIState = MutableStateFlow<StatisticsUIState>(StatisticsUIState())
    val statisticsUIState = _statisticsUIState.asStateFlow()

    fun onEvent(event: StatisticsUIEvent) = when (event) {
        is StatisticsUIEvent.ChangeStudent ->
            viewModelScope.launch {
                _statisticsUIState.value = _statisticsUIState.value.copy(
                    curStudent = event.student
                )
            }
        StatisticsUIEvent.ChangeStudentDrawerState ->
            viewModelScope.launch {
                _statisticsUIState.value = _statisticsUIState.value.copy(
                    isStudentDrawerOpen = !_statisticsUIState.value.isStudentDrawerOpen
                )
            }
        is StatisticsUIEvent.ChangeSubject ->
            viewModelScope.launch {
                _statisticsUIState.value = _statisticsUIState.value.copy(
                    curSubject = event.subject
                )
            }
        StatisticsUIEvent.ChangeSubjectDrawerState ->
            viewModelScope.launch {
                _statisticsUIState.value = _statisticsUIState.value.copy(
                    isSubjectDrawerOpen = !_statisticsUIState.value.isSubjectDrawerOpen
                )
            }
    }

    init {
        viewModelScope.launch {

            launch(Dispatchers.IO) {
                subjectUseCases.GetAllSubjectsUseCase().collect {
                    _allSubjects.value = it.toPresentation(SubjectDomain::toPresentation)
                }
            }

            launch(Dispatchers.IO) {
                studentUseCases.GetAllStudentsUseCase().collect {
                    _allStudents.value = it.toPresentation(StudentDomain::toPresentation)
                }
            }

            launch {
                _statisticsUIState.collectLatest {
                    if (it.curSubject.isNotEmpty() && it.curStudent.isNotEmpty())
                        _statisticsUIState.value = _statisticsUIState.value.copy(
                            mode = 3
                        )
                    else if (it.curSubject.size == 1)
                        _statisticsUIState.value = _statisticsUIState.value.copy(
                            mode = 2
                        )
                    else if (it.curStudent.size == 1)
                        _statisticsUIState.value = _statisticsUIState.value.copy(
                            mode = 1
                        )
                }
            }

        }
    }
}