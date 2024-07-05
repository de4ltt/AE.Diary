package com.example.deathnote.presentation.mapper

import com.example.deathnote.domain.model.SubjectDomain
import com.example.deathnote.presentation.model.Subject

fun Subject.toDomain() = SubjectDomain(
    id = id,
    name = name,
    type = type
)