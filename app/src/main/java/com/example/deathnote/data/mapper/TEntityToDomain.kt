@file:Suppress("UNCHECKED_CAST")

package com.example.deathnote.data.mapper

import com.example.deathnote.data.model.Absences
import com.example.deathnote.data.model.Certificates
import com.example.deathnote.data.model.DataEntity
import com.example.deathnote.data.model.Statistics1M
import com.example.deathnote.data.model.StatisticsM1
import com.example.deathnote.data.model.StatisticsMM
import com.example.deathnote.data.model.Students
import com.example.deathnote.data.model.Subjects
import com.example.deathnote.data.model.Timetables
import com.example.deathnote.domain.model.AbsenceDomain
import com.example.deathnote.domain.model.CertificateDomain
import com.example.deathnote.domain.model.DomainModel
import com.example.deathnote.domain.model.StatisticDomain1M
import com.example.deathnote.domain.model.StatisticDomainM1
import com.example.deathnote.domain.model.StatisticDomainMM
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
        subjectId = subjectId,
        date = date
    )

    is Statistics1M -> StatisticDomain1M(
        studentId = studentId,
        subjectId = subjectId,
        absence = absence,
        resAbsence = resAbsence,
        absencePercent = absencePercent
    )

    is StatisticsM1 -> StatisticDomainM1(
        studentId = studentId,
        subjectId = subjectId,
        absence = absence,
        resAbsence = resAbsence,
        absencePercent = absencePercent
    )

    is StatisticsMM -> StatisticDomainMM(
        subjectId = subjectId,
        presencePercent = presencePercent,
        resAbsencePercent = resAbsencePercent,
        absencePercent = absencePercent
    )

    else -> throw IllegalArgumentException(
        "Unsupported DataEntity type: ${this::class.simpleName}"
    )
} as V