package com.ae_diary.domain.model

import com.ae_diary.domain.model.interfaces.DomainModel

data class StudentDomain(
    val id: Int,
    val name: String,
    val surname: String,
    val patronymic: String
): DomainModel