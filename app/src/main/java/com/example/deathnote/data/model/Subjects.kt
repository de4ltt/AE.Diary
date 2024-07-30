package com.example.deathnote.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.deathnote.data.model.interfaces.DataEntity

@Entity
data class Subjects(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val type : String,
) : DataEntity