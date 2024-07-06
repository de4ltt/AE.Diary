package com.example.deathnote.data.mapper

import com.example.deathnote.data.model.Timetables
import com.example.deathnote.domain.model.TimetableDomain

fun TimetableDomain.toEntity() = Timetables(
    id = id,
    dayOfWeek = dayOfWeek,
    subjectId = subjectId,
    startTime = startTime,
    endTime = endTime
)