package com.example.ae_diary.domain.model

import com.example.ae_diary.domain.model.interfaces.DomainModel

data class AbsenceDomain(
    val studentId: Int,
    val timetableId: Int,
    val respectful: Boolean = false
): DomainModel