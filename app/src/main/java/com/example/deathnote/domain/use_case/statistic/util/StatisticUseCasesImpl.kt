package com.example.deathnote.domain.use_case.statistic.util

import com.example.deathnote.domain.use_case.statistic.GetStatistics1MUseCase
import com.example.deathnote.domain.use_case.statistic.GetStatisticsM1UseCase
import com.example.deathnote.domain.use_case.statistic.GetStatisticsMMUseCase
import javax.inject.Inject

data class StatisticUseCasesImpl @Inject constructor(
    override val GetStatistics1MUseCase: GetStatistics1MUseCase,
    override val GetStatisticsM1UseCase: GetStatisticsM1UseCase,
    override val GetStatisticsMMUseCase: GetStatisticsMMUseCase
): StatisticUseCases
