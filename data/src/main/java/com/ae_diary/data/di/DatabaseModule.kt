package com.ae_diary.data.di

import android.content.Context
import androidx.room.Room
import com.ae_diary.data.repository.database.DiaryDatabase
import com.ae_diary.data.repository.database.dao.AbsencesDAO
import com.ae_diary.data.repository.database.dao.CertificatesDAO
import com.ae_diary.data.repository.database.dao.StudentsDAO
import com.ae_diary.data.repository.database.dao.SubjectsDAO
import com.ae_diary.data.repository.database.dao.TimetablesDAO
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
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideStudentDao(database: DiaryDatabase): StudentsDAO =
        database.studentDAO()

    @Provides
    fun provideSubjectDao(database: DiaryDatabase): SubjectsDAO =
        database.subjectDAO()

    @Provides
    fun provideTimetableDao(database: DiaryDatabase): TimetablesDAO =
        database.timetableDAO()

    @Provides
    fun provideCertificateDao(database: DiaryDatabase): CertificatesDAO =
        database.certificateDAO()

    @Provides
    fun provideAbsenceDao(database: DiaryDatabase): AbsencesDAO =
        database.absenceDAO()

}