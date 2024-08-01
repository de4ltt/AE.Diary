package com.example.deathnote.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.deathnote.data.model.interfaces.DataEntity

@Entity
data class Students(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val surname: String,
    val patronymic: String
) : DataEntity