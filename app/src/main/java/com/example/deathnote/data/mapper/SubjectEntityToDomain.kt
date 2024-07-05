package com.example.deathnote.data.mapper

import com.example.deathnote.data.model.Subjects
import com.example.deathnote.domain.model.SubjectDomain

fun Subjects.toDomain() = SubjectDomain(
    id = id,
    name = name,
    type = type
)