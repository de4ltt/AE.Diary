package com.example.deathnote.di

import com.example.deathnote.data.repository.StatisticsRepositoryImpl
import com.example.deathnote.domain.repository.StatisticsRepository
import com.example.deathnote.domain.use_case.statistic.util.StatisticUseCases
import com.example.deathnote.domain.use_case.statistic.util.StatisticUseCasesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StatisticsModule {

    @Binds
    @Singleton
    abstract fun bindStatisticsRepository(
        statisticsRepositoryImpl: StatisticsRepositoryImpl
    ): StatisticsRepository

    @Binds
    abstract fun bindStatisticsUseCases(
        statisticsUseCasesImpl: StatisticUseCasesImpl
    ): StatisticUseCases

}