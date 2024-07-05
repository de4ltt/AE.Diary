package com.example.deathnote.presentation.mapper

import com.example.deathnote.domain.model.TimetableDomain

fun List<TimetableDomain>.toPresentation() = map { it.toPresentation() }