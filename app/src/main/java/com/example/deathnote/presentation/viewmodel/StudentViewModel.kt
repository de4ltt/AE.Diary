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
import com.example.deathnote.presentation.model.state.StudentDialogState
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

    private val _studentDialogState: MutableStateFlow<StudentDialogState> =
        MutableStateFlow(StudentDialogState())
    val studentDialogState: StateFlow<StudentDialogState> = _studentDialogState.asStateFlow()


    fun onEvent(event: StudentUIEvent) {
        when (event) {

            is StudentUIEvent.DeleteStudent ->
                deleteStudent(event.student)

            is StudentUIEvent.GetStudentById ->
                getStudentById(event.id)

            is StudentUIEvent.UpsertStudent ->
                upsertStudent(event.student)


            is StudentUIEvent.ChangeDialogContent -> {
                _studentDialogState.value = _studentDialogState.value.copy(
                    student = event.student,
                    title = event.title,
                    onAcceptRequest = event.onAcceptRequest,
                    onDismissRequest = event.onDismissRequest,
                    refuseButtonTitle = event.refuseButtonTitle,
                    acceptButtonTitle = event.acceptButtonTitle
                )
            }

            is StudentUIEvent.SelectStudent ->
                _studentDialogState.value =
                    _studentDialogState.value.copy(student = event.student)

            StudentUIEvent.IdleStudent -> _studentDialogState.value =
                _studentDialogState.value.copy(student = Student())

            is StudentUIEvent.ChangeDialogState ->
                _studentDialogState.value = _studentDialogState.value.copy(isShown = event.state)

            is StudentUIEvent.ChangeStudentName ->
                _studentDialogState.value =
                    _studentDialogState.value.copy(
                        student = studentDialogState.value.student.copy(name = event.name)
                    )

            is StudentUIEvent.ChangeStudentSurname ->
                _studentDialogState.value =
                    _studentDialogState.value.copy(
                        student = studentDialogState.value.student.copy(surname = event.surname)
                    )

            is StudentUIEvent.ChangeStudentPatronymic ->
                _studentDialogState.value =
                    _studentDialogState.value.copy(
                        student = _studentDialogState.value.student.copy(patronymic = event.patronymic)
                    )
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