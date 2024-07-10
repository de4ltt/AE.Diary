package com.example.deathnote.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class SubjectDismissedDomain(
    val id: Int? = null,
    val day: String,
    val subjectId: String
): DomainModel