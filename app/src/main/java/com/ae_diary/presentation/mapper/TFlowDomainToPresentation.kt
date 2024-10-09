package com.ae_diary.presentation.mapper

import com.ae_diary.domain.model.interfaces.DomainModel
import com.ae_diary.presentation.model.interfaces.PresentationModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <T: DomainModel, V: PresentationModel> Flow<T>.toDomain(): Flow<V> = map { it.toPresentation() }