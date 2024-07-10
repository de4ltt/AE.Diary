package com.example.deathnote.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class AbsenceDomain(
    val studentId: Int? = null,
    val subjectId: Int? = null,
    val date: String? = null,
    val respectful: Boolean? = false
): DomainModel