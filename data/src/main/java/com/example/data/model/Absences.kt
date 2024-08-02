package com.example.ae_diary.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.ae_diary.data.model.interfaces.DataEntity

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