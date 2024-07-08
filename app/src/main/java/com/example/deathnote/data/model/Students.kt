package com.example.deathnote.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Students(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String = "",
    val surname: String = "",
    val patronymic: String = ""
) : DataEntity