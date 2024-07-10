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

fun <T: DomainModel, V: DataEntity> T.toEntity(): V = when (this) {

    is StudentDomain -> Students(
        id = id,
        name = name,
        surname = surname,
        patronymic = patronymic
    )

    is SubjectDomain -> Subjects(
        id = id,
        name = name,
        type = type
    )

    is TimetableDomain -> Timetables(
        id = id,
        dayOfWeek = dayOfWeek,
        subjectId = subjectId,
        startTime = startTime,
        endTime = endTime
    )

    is CertificateDomain -> Certificates(
        id = id,
        studentId = studentId,
        start = start,
        end = end
    )

    is HolidayDomain -> Holidays(
        date = date
    )

    is AbsenceDomain -> Absences(
        respectful = respectful,
        studentId = studentId,
        subjectId = subjectId,
        date = date
    )

    is SubjectDismissedDomain -> SubjectsDismissed(
        day = day,
        subjectId = subjectId
    )

    is WeekTypeDomain -> WeekTypes(
        id = id,
        type = type,
        day = day
    )

    else -> throw IllegalArgumentException(
        "Unsupported DomainModel type: ${this::class.simpleName}"
    )
} as V