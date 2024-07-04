package com.example.deathnote.data.mapper

import com.example.deathnote.data.model.Students
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Flow<List<Students>>.toDomain() = this.map {
    it.map { student -> student.toDomain() }
}