package com.example.deathnote.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class WeekTypeDomain(
    val id: Int,
    val type: String,
    val day: String
): DomainModel