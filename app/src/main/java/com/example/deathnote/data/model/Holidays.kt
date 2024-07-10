package com.example.deathnote.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Holidays(
    @PrimaryKey(autoGenerate = false)
    val date: String
): DataEntity