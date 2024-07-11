package com.example.deathnote.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class AbsenceDomain(
    val studentId: Int,
    val subjectId: Int,
    val date: String,
    val respectful: Boolean = false
): DomainModel