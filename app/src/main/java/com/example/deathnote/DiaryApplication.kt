package com.example.deathnote

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.example.deathnote.data.repository.database.DiaryDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DiaryApplication : Application() {

    companion object {
        val START_TIME = stringPreferencesKey("start_time")
        val END_TIME = stringPreferencesKey("end_time")
        val FIRST_WEEK_TYPE = stringPreferencesKey("first_week_type")
        val HOLIDAYS = stringPreferencesKey("holidays")
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    }

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