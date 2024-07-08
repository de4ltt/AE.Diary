package com.example.deathnote.data.mapper

import com.example.deathnote.data.model.DataEntity
import com.example.deathnote.domain.model.DomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <T: DataEntity, V: DomainModel> Flow<List<T>>.toDomain(transform: (T) -> V): Flow<List<V>> =
    map { list -> list.map(transform) }