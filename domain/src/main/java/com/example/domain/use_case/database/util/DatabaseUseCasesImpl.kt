package com.example.domain.use_case.database.util

import com.example.domain.use_case.database.ExportDatabaseToLocalPathUseCase
import com.example.domain.use_case.database.ImportDatabaseFromLocalPathUseCase
import javax.inject.Inject

data class DatabaseUseCasesImpl @Inject constructor(
    override val ExportDatabaseToLocalPathUseCase: ExportDatabaseToLocalPathUseCase,
    override val ImportDatabaseFromLocalPathUseCase: ImportDatabaseFromLocalPathUseCase
): DatabaseUseCases
