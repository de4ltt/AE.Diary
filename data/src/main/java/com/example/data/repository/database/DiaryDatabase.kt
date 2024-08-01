package com.example.deathnote.data.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.deathnote.data.model.Absences
import com.example.deathnote.data.model.Certificates
import com.example.deathnote.data.model.Students
import com.example.deathnote.data.model.Subjects
import com.example.deathnote.data.model.Timetables
import com.example.deathnote.data.repository.database.dao.AbsencesDAO
import com.example.deathnote.data.repository.database.dao.CertificatesDAO
import com.example.deathnote.data.repository.database.dao.StudentsDAO
import com.example.deathnote.data.repository.database.dao.SubjectsDAO
import com.example.deathnote.data.repository.database.dao.TimetablesDAO

@Database(
    entities = [
        Students::class,
        Subjects::class,
        Timetables::class,
        Certificates::class,
        Absences::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DiaryDatabase: RoomDatabase() {

    abstract fun studentDAO(): StudentsDAO

    abstract fun subjectDAO(): SubjectsDAO

    abstract fun timetableDAO(): TimetablesDAO

    abstract fun certificateDAO(): CertificatesDAO

    abstract fun absenceDAO(): AbsencesDAO

}