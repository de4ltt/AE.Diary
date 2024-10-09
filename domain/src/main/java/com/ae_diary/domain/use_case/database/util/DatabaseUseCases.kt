package com.ae_diary.domain.use_case.database.util

import com.ae_diary.domain.use_case.database.ExportDatabaseToLocalPathUseCase
import com.ae_diary.domain.use_case.database.ImportDatabaseFromLocalPathUseCase

sealed interface DatabaseUseCases {
    val ExportDatabaseToLocalPathUseCase: ExportDatabaseToLocalPathUseCase
    val ImportDatabaseFromLocalPathUseCase: ImportDatabaseFromLocalPathUseCase
}