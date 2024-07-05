package com.example.deathnote.presentation.mapper

import com.example.deathnote.domain.model.SubjectDomain

fun List<SubjectDomain>.toPresentation() = this.map { it.toPresentation() }