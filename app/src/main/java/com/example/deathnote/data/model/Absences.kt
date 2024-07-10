package com.example.deathnote.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    primaryKeys = [
        "studentId", "subjectId", "date"
    ]
)
data class Absences(
    val studentId: Int? = null,
    val subjectId: Int? = null,
    val date: String? = null,
    val respectful: Boolean? = false
): DataEntity