package com.example.deathnote.presentation.mapper

import com.example.deathnote.domain.model.TimetableDomain
import com.example.deathnote.presentation.model.Timetable

fun TimetableDomain.toPresentation() = Timetable(
    id = id,
    dayOfWeek = dayOfWeek,
    subjectId = subjectId,
    startTime = startTime,
    endTime = endTime
)