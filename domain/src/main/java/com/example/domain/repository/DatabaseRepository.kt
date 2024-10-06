package com.example.domain.repository

interface DatabaseRepository {

    suspend fun exportDatabaseToLocalPath(path: String): Boolean

    suspend fun importDatabaseFromLocalPath(path: String): Boolean

}