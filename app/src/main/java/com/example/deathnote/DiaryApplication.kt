package com.example.deathnote

import android.app.Application
import androidx.room.Room
import com.example.deathnote.data.repository.database.DiaryDatabase
import dagger.hilt.android.HiltAndroidApp

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