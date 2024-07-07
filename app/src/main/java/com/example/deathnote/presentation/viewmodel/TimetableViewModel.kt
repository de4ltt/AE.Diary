package com.example.deathnote.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deathnote.domain.use_case.timetable.util.TimetableUseCases
import com.example.deathnote.presentation.mapper.toDomain
import com.example.deathnote.presentation.mapper.toPresentation
import com.example.deathnote.presentation.model.Timetable
import com.example.deathnote.presentation.model.event.TimetableUIEvent
import com.example.deathnote.presentation.model.state.TimetableDialogState
import com.example.deathnote.presentation.model.state.TimetableState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimetableViewModel @Inject constructor(
    private val timetableUseCases: TimetableUseCases
) : ViewModel() {

    private val _allTimetables: MutableStateFlow<List<Timetable>> = MutableStateFlow(emptyList())
    val allTimetables = _allTimetables.asStateFlow()

    private val _timetableState: MutableStateFlow<TimetableState> =
        MutableStateFlow(TimetableState())
    val timetableState = _timetableState.asStateFlow()

    private val _timetableDialogState: MutableStateFlow<TimetableDialogState> =
        MutableStateFlow(TimetableDialogState())
    val timetableDialogState = _timetableDialogState.asStateFlow()

    fun onEvent(event: TimetableUIEvent) {
        when (event) {
            TimetableUIEvent.ChangeWeekType -> viewModelScope.launch(Dispatchers.IO) {
                _timetableState.value = _timetableState.value.copy(
                    weekType = if (timetableState.value.weekType == "Odd") "Even" else "Odd"
                )
            }

            is TimetableUIEvent.UpsertTimetable -> upsertTimetable(event.timetable)
            is TimetableUIEvent.DeleteTimetable -> deleteTimetable(event.timetable)

            is TimetableUIEvent.ChangeDialogDayOfWeek -> viewModelScope.launch(Dispatchers.IO) {
                _timetableDialogState.value = _timetableDialogState.value.copy(
                    selectedDay = event.dayOfWeek
                )
            }

            is TimetableUIEvent.ChangeDialogEndTime -> viewModelScope.launch(Dispatchers.IO) {
                _timetableDialogState.value = _timetableDialogState.value.copy(
                    endTime = event.endTime
                )
            }

            is TimetableUIEvent.ChangeDialogStartTime -> viewModelScope.launch(Dispatchers.IO) {
                _timetableDialogState.value = _timetableDialogState.value.copy(
                    startTime = event.startTime
                )
            }

            is TimetableUIEvent.ChangeDialogSubject -> viewModelScope.launch(Dispatchers.IO) {
                _timetableDialogState.value = _timetableDialogState.value.copy(
                    subject = event.subject
                )
            }

            is TimetableUIEvent.ChangeDialogState -> viewModelScope.launch(Dispatchers.IO) {
                _timetableDialogState.value = _timetableDialogState.value.copy(
                    isShown = event.isShown
                )
            }

            is TimetableUIEvent.ChangeDialogSubjectPickerState -> viewModelScope.launch(Dispatchers.IO) {
                _timetableDialogState.value = _timetableDialogState.value.copy(
                    isSubjectPickerShown = event.isShown
                )
            }
        }
    }

    private fun upsertTimetable(timetable: Timetable) = viewModelScope.launch(Dispatchers.IO) {
        timetableUseCases.UpsertTimetableUseCase(timetable.toDomain())
    }

    private fun deleteTimetable(timetable: Timetable) = viewModelScope.launch(Dispatchers.IO) {
        timetableUseCases.DeleteTimetableUseCase(timetable.toDomain())
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            timetableUseCases.GetAllTimetablesUseCase().collect {
                _allTimetables.value = it.toPresentation()
            }
        }
    }
}
