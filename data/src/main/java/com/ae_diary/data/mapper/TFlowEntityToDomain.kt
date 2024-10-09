package com.ae_diary.data.mapper

import com.ae_diary.data.model.interfaces.DataEntity
import com.ae_diary.domain.model.interfaces.DomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <T: DataEntity, V: DomainModel> Flow<T>.toDomain(): Flow<V> = map { it.toDomain() }