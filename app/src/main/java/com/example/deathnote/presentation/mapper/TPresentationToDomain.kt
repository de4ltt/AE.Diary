@file:Suppress("UNCHECKED_CAST")

package com.example.deathnote.presentation.mapper

import com.example.deathnote.data.model.DataEntity
import com.example.deathnote.data.model.Students
import com.example.deathnote.data.model.Subjects
import com.example.deathnote.data.model.Timetables
import com.example.deathnote.domain.model.DomainModel
import com.example.deathnote.domain.model.StudentDomain
import com.example.deathnote.domain.model.SubjectDomain
import com.example.deathnote.domain.model.TimetableDomain
import com.example.deathnote.presentation.model.PresentationModel
import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.Timetable

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
        dayOfWeek = dayOfWeek,
        subjectId = subjectId,
        startTime = startTime,
        endTime = endTime
    )

    else -> throw IllegalArgumentException(
        "Unsupported DataEntity type: ${this::class.simpleName}"
    )
} as V