package com.example.domain.use_case.database

import com.example.domain.repository.DatabaseRepository
import javax.inject.Inject

class ImportDatabaseFromLocalPathUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
){

    suspend operator fun invoke(path: String): Boolean =
        databaseRepository.importDatabaseFromLocalPath(path)
}