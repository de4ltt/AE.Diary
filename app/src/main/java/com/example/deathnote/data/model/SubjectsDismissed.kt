package com.example.deathnote.data.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    primaryKeys = [
        "day", "subjectId"
    ]
)
data class SubjectsDismissed(
    val day: String,
    val subjectId: Int
): DataEntity