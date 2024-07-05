package com.example.deathnote.di

import android.content.Context
import androidx.room.Room
import com.example.deathnote.data.repository.database.DiaryDatabase
import com.example.deathnote.data.repository.database.dao.StudentsDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDiaryDatabase(@ApplicationContext context: Context): DiaryDatabase {
        return Room.databaseBuilder(
            context,
            DiaryDatabase::class.java,
            "diary_database"
        ).build()
    }

    @Provides
    fun provideStudentDao(database: DiaryDatabase): StudentsDAO =
        database.studentDAO()


}