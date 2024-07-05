package com.example.deathnote.data.mapper

import com.example.deathnote.data.model.Subjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


fun Flow<List<Subjects>>.toDomain() = this.map { list ->
    list.map { it.toDomain() }
}