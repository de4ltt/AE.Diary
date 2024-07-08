@file:Suppress("UNCHECKED_CAST")

package com.example.deathnote.data.mapper

import com.example.deathnote.data.model.Certificates
import com.example.deathnote.data.model.DataEntity
import com.example.deathnote.data.model.Students
import com.example.deathnote.data.model.Subjects
import com.example.deathnote.data.model.Timetables
import com.example.deathnote.domain.model.CertificateDomain
import com.example.deathnote.domain.model.DomainModel
import com.example.deathnote.domain.model.StudentDomain
import com.example.deathnote.domain.model.SubjectDomain
import com.example.deathnote.domain.model.TimetableDomain

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

    else -> throw IllegalArgumentException(
        "Unsupported DataEntity type: ${this::class.simpleName}"
    )
} as V