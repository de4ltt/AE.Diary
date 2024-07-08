package com.example.deathnote.presentation.mapper

import com.example.deathnote.domain.model.DomainModel
import com.example.deathnote.presentation.model.PresentationModel

fun <T: DomainModel, V: PresentationModel> List<T>.toPresentation(transform: (T) -> V): List<V> =
    this.map(transform)