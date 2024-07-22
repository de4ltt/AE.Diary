package com.example.deathnote.domain.use_case.statistic.util

import com.example.deathnote.domain.use_case.statistic.GetStatistics1MUseCase
import com.example.deathnote.domain.use_case.statistic.GetStatisticsM1UseCase
import com.example.deathnote.domain.use_case.statistic.GetStatisticsMMUseCase

sealed interface StatisticUseCases {
    val GetStatistics1MUseCase: GetStatistics1MUseCase
    val GetStatisticsM1UseCase: GetStatisticsM1UseCase
    val GetStatisticsMMUseCase: GetStatisticsMMUseCase
}