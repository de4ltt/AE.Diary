package com.example.deathnote.domain.model

import com.example.deathnote.domain.model.interfaces.DomainModel

data class SubjectDomain(
    val id: Int,
    val name: String,
    val type : String
): DomainModel