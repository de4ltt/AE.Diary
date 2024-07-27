package com.example.deathnote.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Students::class,
            parentColumns = [
                "id"
            ],
            childColumns = [
                "studentId"
            ],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Certificates(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val studentId: Int,
    val start: String,
    val end: String
): DataEntity
