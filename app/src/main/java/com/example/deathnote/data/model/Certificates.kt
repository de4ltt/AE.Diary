package com.example.deathnote.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Certificates(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val studentId: Int? = null,
    val start: String = "08:00",
    val end: String = "12:00",
): DataEntity
