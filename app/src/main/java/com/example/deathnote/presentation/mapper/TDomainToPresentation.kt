package com.example.deathnote.presentation.mapper

import com.example.deathnote.data.model.DataEntity
import com.example.deathnote.data.model.Students
import com.example.deathnote.data.model.Subjects
import com.example.deathnote.data.model.Timetables
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

fun <T: DomainModel, V: PresentationModel> T.toPresentation(): V = when (this) {

    is StudentDomain -> Student(
        id = id,
        name = name,
        surname = surname,
        patronymic = patronymic
    )

    is SubjectDomain -> Subject(
        id = id,
        name = name,
        type = type
    )

    is TimetableDomain -> Timetable(
        id = id,
        dayOfWeek = dayOfWeek,
        subjectId = subjectId,
        startTime = startTime,
        endTime = endTime
    )

    is CertificateDomain -> Certificate(
        id = id,
        studentId = studentId,
        start = start,
        end = end
    )

    is HolidayDomain -> Holiday(
        date = date
    )

    is AbsenceDomain -> Absence(
        respectful = respectful,
        studentId = studentId,
        subjectId = subjectId,
        date = date
    )

    is SubjectDismissedDomain -> SubjectDismissed(
        day = day,
        subjectId = subjectId
    )

    is WeekTypeDomain -> WeekType(
        type = type,
        day = day
    )

    else -> throw IllegalArgumentException(
        "Unsupported DomainModel type: ${this::class.simpleName}"
    )
} as V