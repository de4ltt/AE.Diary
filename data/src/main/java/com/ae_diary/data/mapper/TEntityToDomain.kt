@file:Suppress("UNCHECKED_CAST")

package com.ae_diary.data.mapper

import com.ae_diary.data.model.Absences
import com.ae_diary.data.model.Certificates
import com.ae_diary.data.model.Students
import com.ae_diary.data.model.Subjects
import com.ae_diary.data.model.Timetables
import com.ae_diary.data.model.interfaces.DataEntity
import com.ae_diary.domain.model.AbsenceDomain
import com.ae_diary.domain.model.CertificateDomain
import com.ae_diary.domain.model.StudentDomain
import com.ae_diary.domain.model.SubjectDomain
import com.ae_diary.domain.model.TimetableDomain
import com.ae_diary.domain.model.interfaces.DomainModel

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
        date = date,
        subjectId = subjectId,
        startTime = startTime,
        endTime = endTime,
        weekType = weekType,
        isDismissed = isDismissed
    )

    is Certificates -> CertificateDomain(
        id = id,
        studentId = studentId,
        start = start,
        end = end
    )

    is Absences -> AbsenceDomain(
        respectful = respectful,
        studentId = studentId,
        timetableId = timetableId
    )

    else -> throw IllegalArgumentException(
        "Unsupported DataEntity type: ${this::class.simpleName}"
    )
} as V