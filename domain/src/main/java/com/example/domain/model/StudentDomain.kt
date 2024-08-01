package com.example.deathnote.domain.model

import com.example.deathnote.domain.model.interfaces.DomainModel

data class StudentDomain(
    val id: Int,
    val name: String,
    val surname: String,
    val patronymic: String
): DomainModel