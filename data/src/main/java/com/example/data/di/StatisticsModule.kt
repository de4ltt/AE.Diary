package com.example.data.di

import com.example.ae_diary.domain.use_case.statistic.util.StatisticUseCases
import com.example.ae_diary.domain.use_case.statistic.util.StatisticUseCasesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StatisticsModule {

    @Binds
    abstract fun bindStatisticsUseCases(
        statisticsUseCasesImpl: StatisticUseCasesImpl
    ): StatisticUseCases

}