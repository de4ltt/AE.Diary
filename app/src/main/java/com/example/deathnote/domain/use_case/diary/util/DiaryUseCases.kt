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
import com.example.deathnote.domain.use_case.diary.GetWeekTypeByDayUseCase

sealed interface DiaryUseCases {

    val AddDismissedSubjectUseCase: AddDismissedSubjectUseCase
    val AddHolidayUseCase: AddHolidayUseCase
    val AddStudentAbsenceUseCase: AddStudentAbsenceUseCase
    val AddStudentRespectfulAbsenceUseCase: AddStudentRespectfulAbsenceUseCase
    val AddWeekTypeUseCase: AddWeekTypeUseCase
    val DeleteAllWeekTypeUseCase: DeleteAllWeekTypeUseCase
    val DeleteDismissedSubjectUseCase: DeleteDismissedSubjectUseCase
    val DeleteHolidayUseCase: DeleteHolidayUseCase
    val DeleteStudentAbsenceUseCase: DeleteStudentAbsenceUseCase
    val GetAllDayAbsenceUseCase: GetAllDayAbsenceUseCase
    val GetAllDaySubjectsDismissed: GetAllDaySubjectsDismissed
    val GetAllHolidaysUseCase: GetAllHolidaysUseCase
    val GetWeekTypeByDayUseCase: GetWeekTypeByDayUseCase
}