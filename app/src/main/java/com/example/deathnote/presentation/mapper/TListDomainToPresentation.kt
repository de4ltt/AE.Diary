package com.example.deathnote.presentation.mapper

import com.example.deathnote.domain.model.interfaces.DomainModel
import com.example.deathnote.presentation.model.interfaces.PresentationModel

fun <T: DomainModel, V: PresentationModel> List<T>.toPresentation(transform: (T) -> V): List<V> =
    this.map(transform)