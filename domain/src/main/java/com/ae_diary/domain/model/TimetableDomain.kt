package com.ae_diary.domain.model

import com.ae_diary.domain.model.interfaces.DomainModel

data class TimetableDomain(
    val id: Int,
    val date: String,
    val subjectId: Int,
    val startTime: String,
    val endTime: String,
    val weekType: String,
    val isDismissed: Boolean
): DomainModel
