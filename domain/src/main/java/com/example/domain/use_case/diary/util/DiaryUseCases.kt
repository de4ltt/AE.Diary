package com.example.deathnote.domain.use_case.diary.util

import com.example.deathnote.domain.use_case.diary.AddStudentAbsenceUseCase
import com.example.deathnote.domain.use_case.diary.DeleteStudentAbsenceUseCase
import com.example.deathnote.domain.use_case.diary.DismissSubjectUseCase
import com.example.deathnote.domain.use_case.diary.GetAllAbsenceUseCase
import com.example.deathnote.domain.use_case.diary.UndismissSubjectUseCase
import com.example.deathnote.domain.use_case.subject.GetAllSubjectsUseCase
import com.example.deathnote.domain.use_case.timetable.GetAllTimetablesUseCase

sealed interface DiaryUseCases {
    val AddStudentAbsenceUseCase: AddStudentAbsenceUseCase
    val DeleteStudentAbsenceUseCase: DeleteStudentAbsenceUseCase
    val DismissSubjectUseCase: DismissSubjectUseCase
    val UndismissSubjectUseCase: UndismissSubjectUseCase
    val GetAllAbsenceUseCase: GetAllAbsenceUseCase
    val GetAllTimetablesUseCase: GetAllTimetablesUseCase
    val GetAllSubjectsUseCase: GetAllSubjectsUseCase
}