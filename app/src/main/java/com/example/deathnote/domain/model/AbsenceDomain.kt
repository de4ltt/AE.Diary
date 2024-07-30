package com.example.deathnote.domain.model

import com.example.deathnote.domain.model.interfaces.DomainModel

data class AbsenceDomain(
    val studentId: Int,
    val timetableId: Int,
    val respectful: Boolean = false
): DomainModel