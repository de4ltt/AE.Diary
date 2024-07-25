package com.example.deathnote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deathnote.domain.use_case.diary.util.DiaryUseCases
import com.example.deathnote.presentation.mapper.toDomain
import com.example.deathnote.presentation.model.Timetable
import com.example.deathnote.presentation.model.event.DiaryUIEvent
import com.example.deathnote.presentation.model.state.DiaryUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val diaryUseCases: DiaryUseCases,
) : ViewModel() {

    private val _diaryUIState: MutableStateFlow<DiaryUIState> = MutableStateFlow(DiaryUIState())
    val diaryUIState = _diaryUIState.asStateFlow()

    fun onEvent(event: DiaryUIEvent) = when (event) {

        else -> {}
    }

    private fun upsertTimetable(timetable: Timetable) =
        viewModelScope.launch(Dispatchers.IO) {
            diaryUseCases.UpsertTimetableUseCase(timetable.toDomain())
        }
}