package com.example.deathnote.domain.use_case.diary.util

import com.example.deathnote.domain.use_case.diary.AddDismissedSubjectUseCase
import com.example.deathnote.domain.use_case.diary.AddHolidayUseCase
import com.example.deathnote.domain.use_case.diary.AddStudentAbsenceUseCase
import com.example.deathnote.domain.use_case.diary.AddStudentRespectfulAbsenceUseCase
import com.example.deathnote.domain.use_case.diary.AddWeekTypeUseCase
import com.example.deathnote.domain.use_case.diary.DeleteAllWeekTypeUseCase
import com.example.deathnote.domain.use_case.diary.DeleteDismissedSubjectUseCase
import com.example.deathnote.domain.use_case.diary.DeleteHolidayUseCase
import com.example.deathnote.domain.use_case.diary.DeleteStudentAbsenceUseCase
import com.example.deathnote.domain.use_case.diary.GetAllDayAbsenceUseCase
import com.example.deathnote.domain.use_case.diary.GetAllDaySubjectsDismissed
import com.example.deathnote.domain.use_case.diary.GetAllHolidaysUseCase
import com.example.deathnote.domain.use_case.diary.GetWeekTypesUseCase
import javax.inject.Inject

data class DiaryUseCasesImpl @Inject constructor(

    override val AddDismissedSubjectUseCase: AddDismissedSubjectUseCase,
    override val AddHolidayUseCase: AddHolidayUseCase,
    override val AddStudentAbsenceUseCase: AddStudentAbsenceUseCase,
    override val AddStudentRespectfulAbsenceUseCase: AddStudentRespectfulAbsenceUseCase,
    override val AddWeekTypeUseCase: AddWeekTypeUseCase,
    override val DeleteAllWeekTypeUseCase: DeleteAllWeekTypeUseCase,
    override val DeleteDismissedSubjectUseCase: DeleteDismissedSubjectUseCase,
    override val DeleteHolidayUseCase: DeleteHolidayUseCase,
    override val DeleteStudentAbsenceUseCase: DeleteStudentAbsenceUseCase,
    override val GetAllDayAbsenceUseCase: GetAllDayAbsenceUseCase,
    override val GetAllDaySubjectsDismissed: GetAllDaySubjectsDismissed,
    override val GetAllHolidaysUseCase: GetAllHolidaysUseCase,
    override val GetWeekTypesUseCase: GetWeekTypesUseCase,
    ): DiaryUseCases
