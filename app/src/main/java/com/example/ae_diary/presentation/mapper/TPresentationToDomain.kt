@file:Suppress("UNCHECKED_CAST")

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
        date = date,
        subjectId = subjectId,
        startTime = startTime,
        endTime = endTime,
        weekType = weekType,
        isDismissed = isDismissed
    )

    is Certificate -> CertificateDomain(
        id = id,
        studentId = studentId,
        start = start,
        end = end
    )

    is Absence -> AbsenceDomain(
        respectful = respectful,
        studentId = studentId,
        timetableId = timetableId
    )

    else -> throw IllegalArgumentException(
        "Unsupported DataEntity type: ${this::class.simpleName}"
    )
} as V