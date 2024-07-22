package com.example.deathnote.domain.repository

import com.example.deathnote.domain.model.StatisticDomain1M
import com.example.deathnote.domain.model.StatisticDomainM1
import com.example.deathnote.domain.model.StatisticDomainMM
import kotlinx.coroutines.flow.Flow

interface StatisticsRepository {

    suspend fun getStatistics1M(): Flow<List<StatisticDomain1M>>
    suspend fun getStatisticsM1(): Flow<List<StatisticDomainM1>>
    suspend fun getStatisticsMM(): Flow<List<StatisticDomainMM>>

}