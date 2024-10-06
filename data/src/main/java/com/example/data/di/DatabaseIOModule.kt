package com.example.data.di

import com.example.data.repository.DatabaseRepositoryImpl
import com.example.domain.repository.DatabaseRepository
import com.example.domain.use_case.database.util.DatabaseUseCases
import com.example.domain.use_case.database.util.DatabaseUseCasesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseIOModule {

    @Binds
    abstract fun bindDatabaseUseCases(databaseUseCasesImpl: DatabaseUseCasesImpl): DatabaseUseCases

    @Singleton
    @Binds
    abstract fun bindDatabaseRepository(databaseRepositoryImpl: DatabaseRepositoryImpl): DatabaseRepository
}