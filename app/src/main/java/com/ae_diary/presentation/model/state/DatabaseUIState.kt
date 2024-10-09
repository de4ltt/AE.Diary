package com.ae_diary.presentation.model.state

import com.ae_diary.presentation.model.enums.DatabaseScreenMode

data class DatabaseUIState(
    val ioPath: String = "",

    val mode: DatabaseScreenMode = DatabaseScreenMode.DEFAULT,

    val exportState: Boolean = false,
    val importState: Boolean = false
)
