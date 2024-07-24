package com.example.deathnote.di

import com.example.deathnote.data.repository.TimetableRepositoryImpl
import com.example.deathnote.domain.repository.TimetableRepository
import com.example.deathnote.domain.use_case.timetable.util.TimetableUseCases
import com.example.deathnote.domain.use_case.timetable.util.TimetableUseCasesImpl
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