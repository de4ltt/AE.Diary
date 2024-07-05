package com.example.deathnote.presentation.mapper

import com.example.deathnote.domain.model.SubjectDomain
import com.example.deathnote.presentation.model.Subject

fun SubjectDomain.toPresentation() = Subject(
    id = id,
    name = name,
    type = type
)