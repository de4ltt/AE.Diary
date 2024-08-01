package com.example.deathnote.domain.model

import com.example.deathnote.domain.model.interfaces.DomainModel

data class TimetableDomain(
    val id: Int,
    val date: String,
    val subjectId: Int,
    val startTime: String,
    val endTime: String,
    val weekType: String,
    val isDismissed: Boolean
): DomainModel
