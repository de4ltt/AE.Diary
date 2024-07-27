package com.example.deathnote.data.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = [
        "studentId", "timetableId"
    ],
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
        ),
        ForeignKey(
            entity = Timetables::class,
            parentColumns = [
                "id"
            ],
            childColumns = [
                "timetableId"
            ],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Absences(
    val studentId: Int,
    val timetableId: Int,
    val respectful: Boolean
): DataEntity