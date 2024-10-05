package com.example.ae_diary.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ae_diary.domain.model.StudentDomain
import com.example.ae_diary.domain.use_case.student.util.StudentUseCases
import com.example.ae_diary.presentation.mapper.toDomain
import com.example.ae_diary.presentation.mapper.toPresentation
import com.example.ae_diary.presentation.model.Student
import com.example.ae_diary.presentation.model.event.StudentUIEvent
import com.example.ae_diary.presentation.model.state.StudentDialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
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

            is StudentUIEvent.UpsertStudent ->
                upsertStudent(event.student)


            is StudentUIEvent.ChangeDialogContent -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _studentDialogState.value = _studentDialogState.value.copy(
                        student = event.student,
                        title = event.title,
                        onAcceptRequest = event.onAcceptRequest,
                        onDismissRequest = event.onDismissRequest,
                        refuseButtonTitle = event.refuseButtonTitle,
                        acceptButtonTitle = event.acceptButtonTitle
                    )
                }
            }

            is StudentUIEvent.SelectStudent ->
                viewModelScope.launch(Dispatchers.IO) {
                    _studentDialogState.value =
                        _studentDialogState.value.copy(student = event.student)
                }

            StudentUIEvent.IdleStudent -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _studentDialogState.value =
                        _studentDialogState.value.copy(student = Student())
                }
            }

            is StudentUIEvent.ChangeDialogState ->
                viewModelScope.launch(Dispatchers.IO) {
                    _studentDialogState.value =
                        _studentDialogState.value.copy(isShown = event.state)
                }

            is StudentUIEvent.ChangeStudentName ->
                viewModelScope.launch(Dispatchers.IO) {
                    _studentDialogState.value =
                        _studentDialogState.value.copy(
                            student = studentDialogState.value.student.copy(name = event.name)
                        )
                }

            is StudentUIEvent.ChangeStudentSurname ->
                viewModelScope.launch(Dispatchers.IO) {
                    _studentDialogState.value =
                        _studentDialogState.value.copy(
                            student = studentDialogState.value.student.copy(surname = event.surname)
                        )
                }


            is StudentUIEvent.ChangeStudentPatronymic ->
                viewModelScope.launch(Dispatchers.IO) {
                    _studentDialogState.value =
                        _studentDialogState.value.copy(
                            student = _studentDialogState.value.student.copy(patronymic = event.patronymic)
                        )
                }

            is StudentUIEvent.ChangeDialogTitle ->
                viewModelScope.launch(Dispatchers.IO) {
                    _studentDialogState.value = _studentDialogState.value.copy(title = event.title)
                }
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

    fun getStudentById(id: Int?) = if (id == null) Student()
    else _allStudents.value.filter { it.id == id }[0]


    init {
        viewModelScope.launch(Dispatchers.IO) {
            studentUseCases.GetAllStudentsUseCase().collectLatest {
                _allStudents.value = it.toPresentation(StudentDomain::toPresentation)
            }
        }
    }
}