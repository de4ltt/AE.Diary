package com.example.deathnote.data.mapper

import com.example.deathnote.data.model.DataEntity
import com.example.deathnote.data.model.Students
import com.example.deathnote.data.model.Subjects
import com.example.deathnote.data.model.Timetables
import com.example.deathnote.domain.model.DomainModel
import com.example.deathnote.domain.model.StudentDomain
import com.example.deathnote.domain.model.SubjectDomain
import com.example.deathnote.domain.model.TimetableDomain

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
        dayOfWeek = dayOfWeek,
        subjectId = subjectId,
        startTime = startTime,
        endTime = endTime
    )

    else -> throw IllegalArgumentException(
        "Unsupported DomainModel type: ${this::class.simpleName}"
    )
} as V