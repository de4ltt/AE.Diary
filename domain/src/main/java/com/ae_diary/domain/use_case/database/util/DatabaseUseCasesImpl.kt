package com.ae_diary.domain.use_case.database.util

import com.ae_diary.domain.use_case.database.ExportDatabaseToLocalPathUseCase
import com.ae_diary.domain.use_case.database.ImportDatabaseFromLocalPathUseCase
import javax.inject.Inject

data class DatabaseUseCasesImpl @Inject constructor(
    override val ExportDatabaseToLocalPathUseCase: ExportDatabaseToLocalPathUseCase,
    override val ImportDatabaseFromLocalPathUseCase: ImportDatabaseFromLocalPathUseCase
): DatabaseUseCases
