package com.example.deathnote.di

import com.example.deathnote.data.repository.AbsenceRepositoryImpl
import com.example.deathnote.data.repository.HolidayRepositoryImpl
import com.example.deathnote.data.repository.SubjectDismissedRepositoryImpl
import com.example.deathnote.data.repository.WeekTypeRepositoryImpl
import com.example.deathnote.domain.repository.AbsenceRepository
import com.example.deathnote.domain.repository.HolidayRepository
import com.example.deathnote.domain.repository.SubjectDismissedRepository
import com.example.deathnote.domain.repository.WeekTypeRepository
import com.example.deathnote.domain.use_case.diary.util.DiaryUseCases
import com.example.deathnote.domain.use_case.diary.util.DiaryUseCasesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DiaryModule {

    @Binds
    @Singleton
    abstract fun bindAbsenceRepository(
        absenceRepositoryImpl: AbsenceRepositoryImpl
    ): AbsenceRepository

    @Binds
    @Singleton
    abstract fun bindHolidayRepository(
        holidayRepositoryImpl: HolidayRepositoryImpl
    ): HolidayRepository

    @Binds
    @Singleton
    abstract fun bindSubjectDismissedRepository(
        subjectDismissedRepositoryImpl: SubjectDismissedRepositoryImpl
    ): SubjectDismissedRepository

    @Binds
    @Singleton
    abstract fun bindWeekTypeRepository(
        weekTypeRepositoryImpl: WeekTypeRepositoryImpl
    ): WeekTypeRepository

    @Binds
    abstract fun bindAbsenceUseCases(
        absenceUseCasesImpl: DiaryUseCasesImpl
    ): DiaryUseCases

}