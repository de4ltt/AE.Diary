package com.example.deathnote.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeekTypes(
    @PrimaryKey
    val id: Int,
    val type: String,
    val start: String,
    val end: String
): DataEntity