package com.example.deathnote.presentation.mapper

import com.example.deathnote.domain.model.StudentDomain

fun List<StudentDomain>.toPresentation() = this.map { it.toPresentation() }