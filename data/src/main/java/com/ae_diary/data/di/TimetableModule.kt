package com.ae_diary.data.di

import com.ae_diary.data.repository.TimetableRepositoryImpl
import com.ae_diary.domain.repository.TimetableRepository
import com.ae_diary.domain.use_case.timetable.util.TimetableUseCases
import com.ae_diary.domain.use_case.timetable.util.TimetableUseCasesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TimetableModule {

    @Binds
    abstract fun bindTimetableUseCases(timetableUseCasesImpl: TimetableUseCasesImpl): TimetableUseCases

    @Binds
    abstract fun bindTimetableRepository(timetableRepositoryImpl: TimetableRepositoryImpl): TimetableRepository

}