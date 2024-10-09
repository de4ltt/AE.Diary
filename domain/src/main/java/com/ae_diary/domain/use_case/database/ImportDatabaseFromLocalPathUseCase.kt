package com.ae_diary.domain.use_case.database

import com.ae_diary.domain.repository.DatabaseRepository
import javax.inject.Inject

class ImportDatabaseFromLocalPathUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
){

    suspend operator fun invoke(path: String): Boolean =
        databaseRepository.importDatabaseFromLocalPath(path)
}