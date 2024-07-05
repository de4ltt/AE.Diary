package com.example.deathnote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deathnote.domain.use_case.timetable.util.TimetableUseCases
import com.example.deathnote.presentation.mapper.toPresentation
import com.example.deathnote.presentation.model.Timetable
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

    init {
        viewModelScope.launch(Dispatchers.IO) {
            timetableUseCases.GetAllTimetablesUseCase().collect {
                _allTimetables.value = it.toPresentation()
            }
        }
    }
}