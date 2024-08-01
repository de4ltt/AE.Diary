package com.example.deathnote.data.mapper

import com.example.deathnote.data.model.Absences
import com.example.deathnote.data.model.Certificates
import com.example.deathnote.data.model.Students
import com.example.deathnote.data.model.Subjects
import com.example.deathnote.data.model.Timetables
import com.example.deathnote.data.model.interfaces.DataEntity
import com.example.deathnote.domain.model.AbsenceDomain
import com.example.deathnote.domain.model.CertificateDomain
import com.example.deathnote.domain.model.StudentDomain
import com.example.deathnote.domain.model.SubjectDomain
import com.example.deathnote.domain.model.TimetableDomain
import com.example.deathnote.domain.model.interfaces.DomainModel

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
        date = date,
        subjectId = subjectId,
        startTime = startTime,
        endTime = endTime,
        weekType = weekType,
        isDismissed = isDismissed
    )

    is CertificateDomain -> Certificates(
        id = id,
        studentId = studentId,
        start = start,
        end = end
    )

    is AbsenceDomain -> Absences(
        respectful = respectful,
        studentId = studentId,
        timetableId = timetableId
    )

    else -> throw IllegalArgumentException(
        "Unsupported DomainModel type: ${this::class.simpleName}"
    )

} as V