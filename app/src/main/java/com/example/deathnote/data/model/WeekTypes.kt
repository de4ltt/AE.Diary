package com.example.deathnote.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeekTypes(
    @PrimaryKey
    val day: String,
    val type: String,
): DataEntity