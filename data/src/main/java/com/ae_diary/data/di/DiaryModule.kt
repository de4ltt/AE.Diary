package com.ae_diary.data.di

import com.ae_diary.data.repository.AbsenceRepositoryImpl
import com.ae_diary.domain.repository.AbsenceRepository
import com.ae_diary.domain.use_case.diary.util.DiaryUseCases
import com.ae_diary.domain.use_case.diary.util.DiaryUseCasesImpl
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
    abstract fun bindAbsenceUseCases(
        absenceUseCasesImpl: DiaryUseCasesImpl
    ): DiaryUseCases
}