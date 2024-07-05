package com.example.deathnote.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subjects(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String = "",
    val type : String = "lk"
)