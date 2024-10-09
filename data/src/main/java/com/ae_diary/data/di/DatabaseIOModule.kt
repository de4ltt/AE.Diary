package com.ae_diary.data.di

import android.content.Context
import com.ae_diary.data.repository.DatabaseRepositoryImpl
import com.ae_diary.domain.repository.DatabaseRepository
import com.ae_diary.domain.use_case.database.util.DatabaseUseCases
import com.ae_diary.domain.use_case.database.util.DatabaseUseCasesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseIOModule {

    @Binds
    abstract fun provideContext(@ApplicationContext context: Context): Context

    @Binds
    abstract fun bindDatabaseUseCases(databaseUseCasesImpl: DatabaseUseCasesImpl): DatabaseUseCases

    @Singleton
    @Binds
    abstract fun bindDatabaseRepository(databaseRepositoryImpl: DatabaseRepositoryImpl): DatabaseRepository
}