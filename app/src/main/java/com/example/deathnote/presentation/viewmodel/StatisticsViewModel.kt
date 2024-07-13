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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class StatisticsViewModel @Inject constructor(
    private val subjectUseCases: SubjectUseCases,
    private val studentUseCases: StudentUseCases
) : ViewModel() {

    private val _allSubjects = MutableStateFlow<List<Subject>>(emptyList())
    val allSubjects = _allSubjects.asStateFlow()

    private val _allStudents = MutableStateFlow<List<Student>>(emptyList())
    val allStudents = _allStudents.asStateFlow()

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
        }
    }
}