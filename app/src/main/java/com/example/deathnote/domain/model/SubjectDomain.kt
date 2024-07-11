package com.example.deathnote.domain.model

data class SubjectDomain(
    val id: Int,
    val name: String = "",
    val type : String = "lk"
): DomainModel