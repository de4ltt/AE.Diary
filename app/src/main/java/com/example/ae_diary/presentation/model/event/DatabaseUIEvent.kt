package com.example.ae_diary.presentation.model.event

sealed class DatabaseUIEvent {

    data object ExportDatabaseLocally: DatabaseUIEvent()
    data object ImportDatabaseLocally: DatabaseUIEvent()

    data class ChangeIOPath(val path: String): DatabaseUIEvent()
}
