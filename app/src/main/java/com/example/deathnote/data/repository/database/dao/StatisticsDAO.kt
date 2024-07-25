package com.example.deathnote.data.repository.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.deathnote.data.model.Statistics1M
import com.example.deathnote.data.model.StatisticsM1
import com.example.deathnote.data.model.StatisticsMM
import kotlinx.coroutines.flow.Flow

@Dao
interface StatisticsDAO {

    @Query(
        """
        WITH AllClasses AS (
            SELECT subjectId, count(date) AS count
            FROM Timetables
            WHERE isDismissed = 0
            GROUP BY subjectId
        ),
        
        AllResAbsences AS (
            SELECT studentId, subjectId, count(*) AS count
            FROM Absences ab
            WHERE respectful = 1
            GROUP BY studentId, subjectId
        ),
        
        AllDisresAbsences AS (
            SELECT studentId, subjectId, count(*) AS count
            FROM Absences ab
            WHERE respectful = 0
            GROUP BY studentId, subjectId
        ),
        
        AllAbsences AS (
            SELECT studentId, subjectId, count(*) AS count
            FROM Absences ab
            GROUP BY studentId, subjectId
        )
        
        SELECT
            ad.subjectId,
            ad.studentId,
            COALESCE(ad.count, 0) AS absence,
            COALESCE(aa.count, 0) AS resAbsence,
            COALESCE((a.count / ac.count), 0) AS absencePercent
        FROM AllClasses ac
        JOIN AllAbsences a ON ac.subjectId = a.subjectId
        JOIN AllResAbsences aa ON a.studentId = aa.studentId AND a.subjectId = aa.subjectId
        JOIN AllDisresAbsences ad ON a.studentId = ad.studentId AND a.subjectId = ad.subjectId
        GROUP BY ad.count, aa.count, a.count, ac.count
    """
    )
    fun getStatistics1M(): Flow<List<Statistics1M>>

    @Query(
        """
        WITH TotalClasses AS (
            SELECT subjectId, COUNT(date) AS totalClasses
            FROM Timetables
            WHERE isDismissed = 0
            GROUP BY subjectId
        ),
        
        RespectfulAbsences AS (
            SELECT studentId, subjectId, COUNT(*) AS respectfulAbsences
            FROM Absences
            WHERE respectful = 1
            GROUP BY studentId, subjectId
        ),
        
        DisrespectfulAbsences AS (
            SELECT studentId, subjectId, COUNT(*) AS disrespectfulAbsences
            FROM Absences
            WHERE respectful = 0
            GROUP BY studentId, subjectId
        ),
        
        TotalAbsences AS (
            SELECT studentId, subjectId, SUM(respectfulAbsences + disrespectfulAbsences) AS totalAbsences
            FROM (
                SELECT
                    studentId,
                    subjectId,
                    SUM(CASE WHEN respectful = 1 THEN 1 ELSE 0 END) as respectfulAbsences,
                    SUM(CASE WHEN respectful = 0 THEN 1 ELSE 0 END) as disrespectfulAbsences
                FROM Absences
                GROUP BY studentId, subjectId, date
            )
            GROUP BY studentId, subjectId
        )
        
        SELECT
            ta.subjectId AS subjectId,
            ta.studentId as studentId,
            COALESCE(ra.respectfulAbsences, 0) as resAbsence,
            COALESCE(da.disrespectfulAbsences, 0) as absence,
            COALESCE((da.disrespectfulAbsences / ta.totalAbsences), 0) as absencePercent
        FROM TotalClasses tc
        LEFT JOIN RespectfulAbsences ra ON tc.subjectId = ra.subjectId
        LEFT JOIN DisrespectfulAbsences da ON tc.subjectId = da.subjectId
        LEFT JOIN TotalAbsences ta ON tc.subjectId = ta.subjectId
    """
    )
    fun getStatisticsM1(): Flow<List<StatisticsM1>>

    @Query(
        """
        WITH TotalClasses AS (
            SELECT subjectId, COUNT(date) AS totalClasses
            FROM Timetables
            WHERE isDismissed = 0
            GROUP BY subjectId
        ),
        
        RespectfulAbsences AS (
            SELECT studentId, subjectId, COUNT(DISTINCT date) AS respectfulAbsences
            FROM Absences
            WHERE respectful = 1
            GROUP BY studentId, subjectId
        ),
        
        DisrespectfulAbsences AS (
            SELECT studentId, subjectId, COUNT(DISTINCT date) AS disrespectfulAbsences
            FROM Absences
            WHERE respectful = 0
            GROUP BY studentId, subjectId),
        
        TotalAbsences AS (
            SELECT studentId, subjectId, SUM(respectfulAbsences + disrespectfulAbsences) AS totalAbsences
            FROM (
                SELECT
                    studentId,
                    subjectId,
                    SUM(CASE WHEN respectful = 1 THEN 1 ELSE 0 END) as respectfulAbsences,
                    SUM(CASE WHEN respectful = 0 THEN 1 ELSE 0 END) as disrespectfulAbsences
                FROM Absences
                GROUP BY studentId, subjectId, date
            )
            GROUP BY studentId, subjectId
        )
        
        SELECT
            ta.subjectId AS subjectId,
            COALESCE(((tc.totalClasses - ta.totalAbsences) / tc.totalClasses), 100) AS presencePercent,
            COALESCE((ra.respectfulAbsences / ta.totalAbsences), 100) AS resAbsencePercent,
            COALESCE((da.disrespectfulAbsences / ta.totalAbsences), 0) AS absencePercent
        FROM TotalClasses tc
        LEFT JOIN RespectfulAbsences ra ON tc.subjectId = ra.subjectId
        LEFT JOIN DisrespectfulAbsences da ON tc.subjectId = da.subjectId
        LEFT JOIN TotalAbsences ta ON tc.subjectId = ta.subjectId
    """
    )
    fun getStatisticsMM(): Flow<List<StatisticsMM>>

}