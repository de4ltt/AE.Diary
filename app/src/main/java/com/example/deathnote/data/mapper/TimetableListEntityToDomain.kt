package com.example.deathnote.data.mapper

import com.example.deathnote.data.model.Timetables
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Flow<List<Timetables>>.toDomain() = this.map { list ->
    list.map { it.toDomain() }
}