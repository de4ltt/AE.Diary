package com.example.ae_diary.presentation.mapper

import com.example.ae_diary.domain.model.interfaces.DomainModel
import com.example.ae_diary.presentation.model.interfaces.PresentationModel

fun <T: DomainModel, V: PresentationModel> List<T>.toPresentation(transform: (T) -> V): List<V> =
    this.map(transform)