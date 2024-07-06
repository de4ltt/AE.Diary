package com.example.deathnote.data.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.deathnote.data.model.Students
import com.example.deathnote.data.model.Subjects
import com.example.deathnote.data.model.Timetables
import com.example.deathnote.data.repository.database.dao.StudentsDAO
import com.example.deathnote.data.repository.database.dao.SubjectsDAO
import com.example.deathnote.data.repository.database.dao.TimetablesDAO

@Database(
    entities = [
        Students::class,
        Subjects::class,
        Timetables::class
    ],
    version = 2
)
abstract class DiaryDatabase: RoomDatabase() {

    abstract fun studentDAO(): StudentsDAO

    abstract fun subjectDAO(): SubjectsDAO

    abstract fun timetableDAO(): TimetablesDAO
}