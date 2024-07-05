package com.example.deathnote.data.mapper

import com.example.deathnote.data.model.Subjects
import com.example.deathnote.domain.model.SubjectDomain

fun SubjectDomain.toEntity() = Subjects(
    id = id,
    name = name,
    type = type
)