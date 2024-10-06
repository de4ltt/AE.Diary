package com.example.ae_diary.presentation.model.state

data class DatabaseUIState(
    val ioPath: String = "",

    val exportState: Boolean = false,
    val importState: Boolean = false
)
