package com.example.deathnote.data.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = [
        "studentId", "subjectId", "date"
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
            entity = Subjects::class,
            parentColumns = [
                "id"
            ],
            childColumns = [
                "subjectId"
            ],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Timetables::class,
            parentColumns = [
                "subjectId", "date"
            ],
            childColumns = [
                "subjectId", "date"
            ],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Absences(
    val studentId: Int,
    val subjectId: Int,
    val date: String,
    val respectful: Boolean
): DataEntity