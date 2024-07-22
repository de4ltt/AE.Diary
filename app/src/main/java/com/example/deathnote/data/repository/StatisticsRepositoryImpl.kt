package com.example.deathnote.data.repository

import com.example.deathnote.data.mapper.toDomain
import com.example.deathnote.data.model.Statistics1M
import com.example.deathnote.data.model.StatisticsM1
import com.example.deathnote.data.model.StatisticsMM
import com.example.deathnote.data.repository.database.dao.StatisticsDAO
import com.example.deathnote.domain.model.StatisticDomain1M
import com.example.deathnote.domain.model.StatisticDomainM1
import com.example.deathnote.domain.model.StatisticDomainMM
import com.example.deathnote.domain.repository.StatisticsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StatisticsRepositoryImpl @Inject constructor(
    private val statisticsDAO: StatisticsDAO
): StatisticsRepository {

    override suspend fun getStatistics1M(): Flow<List<StatisticDomain1M>> =
        statisticsDAO.getStatistics1M().toDomain(Statistics1M::toDomain)

    override suspend fun getStatisticsM1(): Flow<List<StatisticDomainM1>> =
        statisticsDAO.getStatisticsM1().toDomain(StatisticsM1::toDomain)

    override suspend fun getStatisticsMM(): Flow<List<StatisticDomainMM>> =
        statisticsDAO.getStatisticsMM().toDomain(StatisticsMM::toDomain)
}