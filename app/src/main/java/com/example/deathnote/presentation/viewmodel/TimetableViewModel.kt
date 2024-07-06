package com.example.deathnote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deathnote.domain.use_case.timetable.util.TimetableUseCases
import com.example.deathnote.presentation.mapper.toDomain
import com.example.deathnote.presentation.mapper.toPresentation
import com.example.deathnote.presentation.model.Timetable
import com.example.deathnote.presentation.model.event.TimetableUIEvent
import com.example.deathnote.presentation.model.state.TimetableState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TimetableViewModel(
    private val timetableUseCases: TimetableUseCases
): ViewModel() {

    private val _allTimetables: MutableStateFlow<List<Timetable>> = MutableStateFlow(emptyList())
    val allTimetables = _allTimetables.asStateFlow()

    private val _timetableState: MutableStateFlow<TimetableState> = MutableStateFlow(TimetableState())
    val timetableState = _timetableState.asStateFlow()

    fun onEvent(event: TimetableUIEvent) {
        when (event) {
            TimetableUIEvent.ChangeWeekType ->
                viewModelScope.launch(Dispatchers.IO) {
                    _timetableState.value = _timetableState.value.copy(
                        weekType = if (_timetableState.value.weekType == "odd") "even" else "odd"
                    )
                }
            is TimetableUIEvent.UpsertTimetable -> upsertTimetable(event.timetable)
            is TimetableUIEvent.DeleteTimetable -> deleteTimetable(event.timetable)
        }
    }

    private fun upsertTimetable(timetable: Timetable) =
        viewModelScope.launch(Dispatchers.IO) {
            timetableUseCases.UpsertTimetableUseCase(timetable.toDomain())
        }

    private fun deleteTimetable(timetable: Timetable) =
        viewModelScope.launch(Dispatchers.IO) {
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