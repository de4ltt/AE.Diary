package com.ae_diary.presentation.model.event

import com.ae_diary.presentation.model.enums.DatabaseScreenMode

sealed class DatabaseUIEvent {

    data object ExportDatabaseLocally: DatabaseUIEvent()
    data object ImportDatabaseLocally: DatabaseUIEvent()

    data class ChangeDatabaseScreenMode(val mode: DatabaseScreenMode): DatabaseUIEvent()

    data class ChangeIOPath(val path: String): DatabaseUIEvent()
}
