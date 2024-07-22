package com.example.deathnote.domain.use_case.statistic

import com.example.deathnote.domain.repository.StatisticsRepository
import javax.inject.Inject

class GetStatisticsM1UseCase @Inject constructor(
    private val statisticsRepository: StatisticsRepository
) {

    suspend operator fun invoke() =
        statisticsRepository.getStatisticsM1()
}