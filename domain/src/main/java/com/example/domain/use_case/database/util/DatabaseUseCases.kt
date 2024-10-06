package com.example.domain.use_case.database.util

import com.example.domain.use_case.database.ExportDatabaseToLocalPathUseCase
import com.example.domain.use_case.database.ImportDatabaseFromLocalPathUseCase

sealed interface DatabaseUseCases {
    val ExportDatabaseToLocalPathUseCase: ExportDatabaseToLocalPathUseCase
    val ImportDatabaseFromLocalPathUseCase: ImportDatabaseFromLocalPathUseCase
}