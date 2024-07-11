package com.example.deathnote.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    primaryKeys = [
        "studentId", "subjectId", "date"
    ]
)
data class Absences(
    val studentId: Int,
    val subjectId: Int,
    val date: String,
    val respectful: Boolean = false
): DataEntity