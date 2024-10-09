package com.ae_diary.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ae_diary.domain.use_case.database.util.DatabaseUseCases
import com.ae_diary.presentation.model.enums.DatabaseScreenMode
import com.ae_diary.presentation.model.event.DatabaseUIEvent
import com.ae_diary.presentation.model.state.DatabaseUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatabaseSettingsViewModel @Inject constructor(
    private val databaseUseCases: DatabaseUseCases
) : ViewModel() {

    private val _databaseUIState: MutableStateFlow<DatabaseUIState> =
        MutableStateFlow(DatabaseUIState())
    val databaseUIState = _databaseUIState.asStateFlow()

    fun onEvent(event: DatabaseUIEvent) = when (event) {
        is DatabaseUIEvent.ChangeIOPath -> changeIOPath(event.path)
        DatabaseUIEvent.ExportDatabaseLocally -> exportDatabaseLocally()
        DatabaseUIEvent.ImportDatabaseLocally -> importDatabaseLocally()
        is DatabaseUIEvent.ChangeDatabaseScreenMode -> changeDatabaseScreenMode(event.mode)
    }

    private fun changeDatabaseScreenMode(mode: DatabaseScreenMode) = viewModelScope.launch {
        _databaseUIState.value = _databaseUIState.value.copy(
            mode = mode
        )
    }

    private fun changeIOPath(path: String) = viewModelScope.launch {
        _databaseUIState.value = _databaseUIState.value.copy(
            ioPath = path
        )
    }

    private fun exportDatabaseLocally() = viewModelScope.launch {
        val result = if (_databaseUIState.value.ioPath.isNotEmpty()) {
            databaseUseCases.ExportDatabaseToLocalPathUseCase(
                _databaseUIState.value.ioPath
            )
        } else false

        _databaseUIState.value = _databaseUIState.value.copy(
            exportState = result
        )
    }

    private fun importDatabaseLocally() = viewModelScope.launch {
        val result = if (_databaseUIState.value.ioPath.isNotEmpty()) {
            databaseUseCases.ImportDatabaseFromLocalPathUseCase(
                _databaseUIState.value.ioPath
            )
        } else false

        _databaseUIState.value = _databaseUIState.value.copy(
            importState = result
        )
    }
}