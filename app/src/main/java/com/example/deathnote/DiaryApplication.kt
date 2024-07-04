package com.example.deathnote

import android.app.Application
import androidx.room.Room
import com.example.deathnote.data.repository.database.DiaryDatabase

class DiaryApplication: Application() {

    companion object {
        lateinit var db: DiaryDatabase
    }

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext,
            DiaryDatabase::class.java,
            "diary.db"
        ).build()
    }
}