package com.example.deathnote.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class SubjectDismissedDomain(
    val day: String,
    val subjectId: Int
): DomainModel