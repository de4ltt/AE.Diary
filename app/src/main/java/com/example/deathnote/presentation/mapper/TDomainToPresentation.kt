package com.example.deathnote.presentation.mapper

import com.example.deathnote.domain.model.AbsenceDomain
import com.example.deathnote.domain.model.CertificateDomain
import com.example.deathnote.domain.model.DomainModel
import com.example.deathnote.domain.model.StatisticDomain1M
import com.example.deathnote.domain.model.StatisticDomainM1
import com.example.deathnote.domain.model.StatisticDomainMM
import com.example.deathnote.domain.model.StudentDomain
import com.example.deathnote.domain.model.SubjectDomain
import com.example.deathnote.domain.model.TimetableDomain
import com.example.deathnote.presentation.model.Absence
import com.example.deathnote.presentation.model.Certificate
import com.example.deathnote.presentation.model.PresentationModel
import com.example.deathnote.presentation.model.Statistic1M
import com.example.deathnote.presentation.model.StatisticM1
import com.example.deathnote.presentation.model.StatisticMM
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
        subjectId = subjectId,
        date = date
    )

    is StatisticDomain1M -> Statistic1M(
        subjectId = subjectId,
        studentId = studentId,
        absence = absence,
        resAbsence = resAbsence,
        absencePercent = absencePercent
    )

    is StatisticDomainM1 -> StatisticM1(
        subjectId = subjectId,
        studentId = studentId,
        absence = absence,
        resAbsence = resAbsence,
        absencePercent = absencePercent
    )

    is StatisticDomainMM -> StatisticMM(
        subjectId = subjectId,
        presencePercent = presencePercent,
        resAbsencePercent = resAbsencePercent,
        absencePercent = absencePercent
    )

    else -> throw IllegalArgumentException(
        "Unsupported DomainModel type: ${this::class.simpleName}"
    )
} as V