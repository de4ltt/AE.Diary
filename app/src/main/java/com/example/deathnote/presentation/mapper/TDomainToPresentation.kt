package com.example.deathnote.presentation.mapper

import com.example.deathnote.data.model.DataEntity
import com.example.deathnote.data.model.Students
import com.example.deathnote.data.model.Subjects
import com.example.deathnote.data.model.Timetables
import com.example.deathnote.domain.model.CertificateDomain
import com.example.deathnote.domain.model.DomainModel
import com.example.deathnote.domain.model.StudentDomain
import com.example.deathnote.domain.model.SubjectDomain
import com.example.deathnote.domain.model.TimetableDomain
import com.example.deathnote.presentation.model.Certificate
import com.example.deathnote.presentation.model.PresentationModel
import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.Timetable

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

    else -> throw IllegalArgumentException(
        "Unsupported DomainModel type: ${this::class.simpleName}"
    )
} as V