@file:Suppress("UNCHECKED_CAST")

package com.example.deathnote.presentation.mapper

import com.example.deathnote.domain.model.AbsenceDomain
import com.example.deathnote.domain.model.CertificateDomain
import com.example.deathnote.domain.model.DomainModel
import com.example.deathnote.domain.model.HolidayDomain
import com.example.deathnote.domain.model.StudentDomain
import com.example.deathnote.domain.model.SubjectDismissedDomain
import com.example.deathnote.domain.model.SubjectDomain
import com.example.deathnote.domain.model.TimetableDomain
import com.example.deathnote.domain.model.WeekTypeDomain
import com.example.deathnote.presentation.model.Absence
import com.example.deathnote.presentation.model.Certificate
import com.example.deathnote.presentation.model.Holiday
import com.example.deathnote.presentation.model.PresentationModel
import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.SubjectDismissed
import com.example.deathnote.presentation.model.Timetable
import com.example.deathnote.presentation.model.WeekType

fun <T: PresentationModel, V: DomainModel> T.toDomain(): V = when (this) {

    is Student -> StudentDomain(
        id = id,
        name = name,
        surname = surname,
        patronymic = patronymic
    )

    is Subject -> SubjectDomain(
        id = id,
        name = name,
        type = type
    )

    is Timetable -> TimetableDomain(
        id = id,
        dayOfWeek = dayOfWeek,
        subjectId = subjectId,
        startTime = startTime,
        endTime = endTime
    )

    is Certificate -> CertificateDomain(
        id = id,
        studentId = studentId,
        start = start,
        end = end
    )

    is Holiday -> HolidayDomain(
        date = date
    )

    is Absence -> AbsenceDomain(
        respectful = respectful,
        studentId = studentId,
        subjectId = subjectId,
        date = date
    )

    is SubjectDismissed -> SubjectDismissedDomain(
        day = day,
        subjectId = subjectId
    )

    is WeekType -> WeekTypeDomain(
        id = id,
        type = type,
        day = day
    )

    else -> throw IllegalArgumentException(
        "Unsupported DataEntity type: ${this::class.simpleName}"
    )
} as V