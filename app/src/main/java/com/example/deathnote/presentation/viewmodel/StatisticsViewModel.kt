package com.example.deathnote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deathnote.domain.model.StatisticDomain1M
import com.example.deathnote.domain.model.StatisticDomainM1
import com.example.deathnote.domain.model.StatisticDomainMM
import com.example.deathnote.domain.use_case.statistic.util.StatisticUseCases
import com.example.deathnote.presentation.mapper.toPresentation
import com.example.deathnote.presentation.model.Statistic1M
import com.example.deathnote.presentation.model.StatisticM1
import com.example.deathnote.presentation.model.StatisticMM
import com.example.deathnote.presentation.model.event.StatisticsUIEvent
import com.example.deathnote.presentation.model.state.StatisticsUIState
import com.example.deathnote.presentation.model.util.StatisticsMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val statisticUseCases: StatisticUseCases
) : ViewModel() {

    private val _allStatistic1M: MutableStateFlow<List<Statistic1M>> = MutableStateFlow(emptyList())
    val allStatistic1M = _allStatistic1M.asStateFlow()

    private val _allStatisticM1: MutableStateFlow<List<StatisticM1>> = MutableStateFlow(emptyList())
    val allStatisticM1 = _allStatisticM1.asStateFlow()

    private val _allStatisticMM: MutableStateFlow<List<StatisticMM>> = MutableStateFlow(emptyList())
    val allStatisticMM = _allStatisticMM.asStateFlow()

    private val _statisticsUIState: MutableStateFlow<StatisticsUIState> =
        MutableStateFlow(StatisticsUIState())
    val statisticsUIState = _statisticsUIState.asStateFlow()

    fun onEvent(event: StatisticsUIEvent) = when (event) {
        is StatisticsUIEvent.ChangeStudent ->
            _statisticsUIState.value = _statisticsUIState.value.copy(
                curStudent = event.student
            )

        StatisticsUIEvent.ChangeStudentDrawerState ->
            _statisticsUIState.value = _statisticsUIState.value.copy(
                isStudentDrawerOpen = !_statisticsUIState.value.isStudentDrawerOpen
            )

        is StatisticsUIEvent.ChangeSubject ->
            _statisticsUIState.value = _statisticsUIState.value.copy(
                curSubject = event.subject
            )

        StatisticsUIEvent.ChangeSubjectDrawerState ->
            _statisticsUIState.value = _statisticsUIState.value.copy(
                isSubjectDrawerOpen = !_statisticsUIState.value.isSubjectDrawerOpen
            )

        is StatisticsUIEvent.ChangeMode -> {
            val curMode = statisticsUIState.value.mode

            if (
                curMode == StatisticsMode.OneStudentManySubjects && event.mode == StatisticsMode.ManyStudentsOneSubject ||
                curMode == StatisticsMode.ManyStudentsOneSubject && event.mode == StatisticsMode.OneStudentManySubjects
            )
                _statisticsUIState.value = _statisticsUIState.value.copy(
                    mode = StatisticsMode.AllStudentsAllSubjects
                )
            else
                _statisticsUIState.value = _statisticsUIState.value.copy(
                    mode = event.mode
                )
        }
    }

    init {
        viewModelScope.launch {

            launch(Dispatchers.IO) {
                statisticUseCases.GetStatistics1MUseCase().collect {
                    _allStatistic1M.value = it.toPresentation(StatisticDomain1M::toPresentation)
                }
            }

            launch(Dispatchers.IO) {
                statisticUseCases.GetStatisticsM1UseCase().collect {
                    _allStatisticM1.value = it.toPresentation(StatisticDomainM1::toPresentation)
                }
            }

            launch(Dispatchers.IO) {
                statisticUseCases.GetStatisticsMMUseCase().collect {
                    _allStatisticMM.value = it.toPresentation(StatisticDomainMM::toPresentation)
                }
            }

        }
    }

}