package com.example.ae_diary.presentation.mapper

import com.example.ae_diary.domain.model.AbsenceDomain
import com.example.ae_diary.domain.model.CertificateDomain
import com.example.ae_diary.domain.model.StudentDomain
import com.example.ae_diary.domain.model.SubjectDomain
import com.example.ae_diary.domain.model.TimetableDomain
import com.example.ae_diary.domain.model.interfaces.DomainModel
import com.example.ae_diary.presentation.model.Absence
import com.example.ae_diary.presentation.model.Certificate
import com.example.ae_diary.presentation.model.Student
import com.example.ae_diary.presentation.model.Subject
import com.example.ae_diary.presentation.model.Timetable
import com.example.ae_diary.presentation.model.interfaces.PresentationModel

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
        date = date,
        subjectId = subjectId,
        startTime = startTime,
        endTime = endTime,
        weekType = weekType,
        isDismissed = isDismissed
    )

    is CertificateDomain -> Certificate(
        id = id,
        studentId = studentId,
        start = start,
        end = end
    )

    is AbsenceDomain -> Absence(
        respectful = respectful,
        studentId = studentId,
        timetableId = timetableId
    )

    else -> throw IllegalArgumentException(
        "Unsupported DomainModel type: ${this::class.simpleName}"
    )
} as V