package com.ae_diary.data.repository

import android.content.Context
import com.ae_diary.domain.repository.DatabaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val context: Context,
) : DatabaseRepository {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun exportDatabaseToLocalPath(path: String): Boolean =
        withContext(ioDispatcher) {
            try {

                val databasePath = context.getDatabasePath("diary_database.db")

                val exportFile = File(path)
                if (!exportFile.exists()) {
                    exportFile.createNewFile()
                }

                val inputStream = FileInputStream(databasePath)
                val outputStream = FileOutputStream(exportFile)

                inputStream.use { input ->
                    outputStream.use { output ->
                        input.copyTo(output)
                    }
                }
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }

    override suspend fun importDatabaseFromLocalPath(path: String): Boolean =
        withContext(ioDispatcher) {
            try {

                val databasePath = context.getDatabasePath("diary_database.db")

                val importFile = File(path)
                if (!importFile.exists()) return@withContext false

                val inputStream = FileInputStream(importFile)
                val outputStream = FileOutputStream(databasePath)

                inputStream.use { input ->
                    outputStream.use { output ->
                        input.copyTo(output)
                    }
                }
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }

}