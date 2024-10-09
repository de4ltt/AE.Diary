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