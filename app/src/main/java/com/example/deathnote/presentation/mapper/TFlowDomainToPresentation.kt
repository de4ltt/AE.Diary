package com.example.deathnote.presentation.mapper

import com.example.deathnote.data.model.DataEntity
import com.example.deathnote.domain.model.DomainModel
import com.example.deathnote.presentation.model.PresentationModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <T: DomainModel, V: PresentationModel> Flow<T>.toDomain(): Flow<V> = map { it.toPresentation() }