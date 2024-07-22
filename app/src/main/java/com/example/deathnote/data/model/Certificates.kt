package com.example.deathnote.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Certificates(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val studentId: Int,
    val start: String,
    val end: String
): DataEntity
