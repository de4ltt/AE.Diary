package com.example.deathnote.domain.use_case.diary.util

import com.example.deathnote.domain.use_case.diary.AddStudentAbsenceUseCase
import com.example.deathnote.domain.use_case.diary.DeleteStudentAbsenceUseCase
import com.example.deathnote.domain.use_case.diary.DismissSubjectUseCase
import com.example.deathnote.domain.use_case.diary.GetAllAbsenceUseCase
import com.example.deathnote.domain.use_case.diary.UndismissSubjectUseCase
import com.example.deathnote.domain.use_case.subject.GetAllSubjectsUseCase
import com.example.deathnote.domain.use_case.timetable.GetAllTimetablesUseCase
import javax.inject.Inject

data class DiaryUseCasesImpl @Inject constructor(
    override val AddStudentAbsenceUseCase: AddStudentAbsenceUseCase,
    override val DeleteStudentAbsenceUseCase: DeleteStudentAbsenceUseCase,
    override val DismissSubjectUseCase: DismissSubjectUseCase,
    override val UndismissSubjectUseCase: UndismissSubjectUseCase,
    override val GetAllAbsenceUseCase: GetAllAbsenceUseCase,
    override val GetAllTimetablesUseCase: GetAllTimetablesUseCase,
    override val GetAllSubjectsUseCase: GetAllSubjectsUseCase
): DiaryUseCases
