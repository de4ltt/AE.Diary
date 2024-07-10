package com.example.deathnote.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class AbsenceDomain(
    val id: Int? = null,
    val studentId: Int? = null,
    val date: String? = null,
): DomainModel