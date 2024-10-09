package com.ae_diary.data.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ae_diary.data.model.Absences
import com.ae_diary.data.model.Certificates
import com.ae_diary.data.model.Students
import com.ae_diary.data.model.Subjects
import com.ae_diary.data.model.Timetables
import com.ae_diary.data.repository.database.dao.AbsencesDAO
import com.ae_diary.data.repository.database.dao.CertificatesDAO
import com.ae_diary.data.repository.database.dao.StudentsDAO
import com.ae_diary.data.repository.database.dao.SubjectsDAO
import com.ae_diary.data.repository.database.dao.TimetablesDAO

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