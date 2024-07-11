package com.example.deathnote.domain.model

data class StudentDomain(
    val id: Int,
    val name: String = "",
    val surname: String = "",
    val patronymic: String = ""
): DomainModel