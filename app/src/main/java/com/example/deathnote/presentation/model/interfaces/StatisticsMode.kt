package com.example.deathnote.presentation.model.interfaces

sealed interface StatisticsMode {

    data object OneStudentManySubjects: StatisticsMode
    data object ManyStudentsOneSubject: StatisticsMode
    data object AllStudentsAllSubjects: StatisticsMode

}