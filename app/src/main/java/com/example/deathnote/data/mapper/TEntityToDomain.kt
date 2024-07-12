@file:Suppress("UNCHECKED_CAST")

package com.example.deathnote.data.mapper

import com.example.deathnote.data.model.Absences
import com.example.deathnote.data.model.Certificates
import com.example.deathnote.data.model.DataEntity
import com.example.deathnote.data.model.Holidays
import com.example.deathnote.data.model.Students
import com.example.deathnote.data.model.Subjects
import com.example.deathnote.data.model.SubjectsDismissed
import com.example.deathnote.data.model.Timetables
import com.example.deathnote.data.model.WeekTypes
import com.example.deathnote.domain.model.AbsenceDomain
import com.example.deathnote.domain.model.CertificateDomain
import com.example.deathnote.domain.model.DomainModel
import com.example.deathnote.domain.model.HolidayDomain
import com.example.deathnote.domain.model.StudentDomain
import com.example.deathnote.domain.model.SubjectDismissedDomain
import com.example.deathnote.domain.model.SubjectDomain
import com.example.deathnote.domain.model.TimetableDomain
import com.example.deathnote.domain.model.WeekTypeDomain

fun <T: DataEntity, V: DomainModel> T.toDomain(): V = when (this) {

    is Students -> StudentDomain(
        id = id,
        name = name,
        surname = surname,
        patronymic = patronymic
    )

    is Subjects -> SubjectDomain(
        id = id,
        name = name,
        type = type
    )

    is Timetables -> TimetableDomain(
        id = id,
        dayOfWeek = dayOfWeek,
        subjectId = subjectId,
        startTime = startTime,
        endTime = endTime
    )

    is Certificates -> CertificateDomain(
        id = id,
        studentId = studentId,
        start = start,
        end = end
    )

    is Holidays -> HolidayDomain(
        date = date
    )

    is Absences -> AbsenceDomain(
        respectful = respectful,
        studentId = studentId,
        subjectId = subjectId,
        date = date
    )

    is SubjectsDismissed -> SubjectDismissedDomain(
        day = day,
        subjectId = subjectId
    )

    is WeekTypes -> WeekTypeDomain(
        type = type,
        day = day
    )

    else -> throw IllegalArgumentException(
        "Unsupported DataEntity type: ${this::class.simpleName}"
    )
} as V