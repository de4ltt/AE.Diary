package com.example.deathnote

import android.app.Application
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.deathnote.data.model.Timetables
import com.example.deathnote.data.repository.database.DiaryDatabase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@HiltAndroidApp
class DiaryApplication : Application() {

    private lateinit var db: DiaryDatabase

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext,
            DiaryDatabase::class.java,
            "diary.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}