package com.example.deathnote.data.mapper

import com.example.deathnote.data.model.Timetables
import com.example.deathnote.domain.model.TimetableDomain

fun Timetables.toDomain() = TimetableDomain(
    dayOfWeek = dayOfWeek,
    subjectId = subjectId,
    startTime = startTime,
    endTime = endTime
)