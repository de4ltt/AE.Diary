package com.example.deathnote.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Absences(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val studentId: Int? = null,
    val date: String? = null,
): DataEntity