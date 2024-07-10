package com.example.deathnote.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SubjectsDismissed(
    @PrimaryKey
    val id: Int? = null,
    val day: String,
    val subjectId: String
): DataEntity