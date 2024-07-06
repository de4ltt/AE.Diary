package com.example.deathnote.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Timetables(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val dayOfWeek: String = "O_1",
    val subjectId: Int? = null,
    val startTime: String? = null,
    val endTime: String? = null
)
