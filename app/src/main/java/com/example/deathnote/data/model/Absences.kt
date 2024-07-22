package com.example.deathnote.data.model

import androidx.room.Entity

@Entity(
    primaryKeys = [
        "studentId", "subjectId", "date"
    ]
)
data class Absences(
    val studentId: Int,
    val subjectId: Int,
    val date: String,
    val respectful: Boolean
): DataEntity