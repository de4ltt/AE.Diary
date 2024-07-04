package com.example.deathnote.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deathnote.domain.use_case.student.util.StudentUseCases
import com.example.deathnote.presentation.mapper.toDomain
import com.example.deathnote.presentation.mapper.toPresentation
import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.event.StudentUIEvent
import com.example.deathnote.presentation.model.state.StudentUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor(
    private val studentUseCases: StudentUseCases
) : ViewModel() {

    private val _allStudents: MutableStateFlow<List<Student>> = MutableStateFlow(emptyList())
    val allStudents: StateFlow<List<Student>> = _allStudents.asStateFlow()

    var studentUIState: StudentUIState by mutableStateOf(StudentUIState())
    private set

    fun onEvent(event: StudentUIEvent) {
        when (event) {
            is StudentUIEvent.DeleteStudent ->
                deleteStudent(event.student)
            is StudentUIEvent.GetStudentById ->
                getStudentById(event.id)
            is StudentUIEvent.UpsertStudent ->
                upsertStudent(event.student)
            is StudentUIEvent.ChangeDeleteModeState ->
                studentUIState = studentUIState.copy(isStudentBarDeleteMode = event.state)
        }
    }

    private fun upsertStudent(student: Student) =
        viewModelScope.launch(Dispatchers.IO) {
            studentUseCases.UpsertStudentUseCase(student.toDomain())
        }

    private fun deleteStudent(student: Student) =
        viewModelScope.launch(Dispatchers.IO) {
            studentUseCases.DeleteStudentUseCase(student.toDomain())
        }

    private fun getStudentById(id: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            studentUseCases.GetStudentById(id)
        }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            studentUseCases.GetAllStudentsUseCase().collect {
                _allStudents.value = it.toPresentation()
            }
        }
    }
}