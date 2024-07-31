package com.example.deathnote.activity.util

import com.example.deathnote.presentation.model.util.DayOfWeek
import com.example.deathnote.presentation.model.util.WeekType
import com.example.deathnote.presentation.util.TimeFormatter.dateFormatter
import java.time.LocalDate

fun curWeekType(date: String, semesterTime: Triple<String, String, WeekType>): WeekType {
    var curDate = LocalDate.parse(date, dateFormatter)
    val semStartTime = LocalDate.parse(semesterTime.first, dateFormatter)
    var semFirstWeekType = semesterTime.third

    while (curDate != semStartTime) {
        curDate = curDate.plusDays(1)

        if (curDate.dayOfWeek.value == DayOfWeek.MONDAY.code)
            semFirstWeekType = if (semFirstWeekType == WeekType.ODD) WeekType.EVEN else WeekType.ODD
    }

    return semFirstWeekType
}