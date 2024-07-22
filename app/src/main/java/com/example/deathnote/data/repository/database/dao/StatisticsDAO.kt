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
            ad.count AS absence,
            aa.count AS resAbsence,
            a.count / ac.count AS absencePercent
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
        )
        
        SELECT
            sub.id AS subjectId,
            s.id AS studentId,
            COALESCE(da.disrespectfulAbsences, 0) AS absence,
            COALESCE(ra.respectfulAbsences, 0) AS resAbsence,
            (COALESCE(da.disrespectfulAbsences, 0) * 100.0 / COALESCE(tc.totalClasses, 1)) AS absencePercent
        FROM Students s
        CROSS JOIN Subjects sub
        LEFT JOIN RespectfulAbsences ra ON s.id = ra.studentId AND sub.id = ra.subjectId
        LEFT JOIN DisrespectfulAbsences da ON s.id = da.studentId AND sub.id = da.subjectId
        LEFT JOIN TotalClasses tc ON sub.id = tc.subjectId
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
            SELECT studentId, subjectId, COUNT(*) AS totalAbsences
            FROM Absences
            GROUP BY studentId, subjectId
        )
        
        SELECT
            sub.id AS subjectId,
            (COALESCE(tc.totalClasses, 0) - COALESCE(ta.totalAbsences, 0)) * 100.0 / COALESCE(tc.totalClasses, 1) AS presencePercent,
            COALESCE(ra.respectfulAbsences, 0) * 100.0 / COALESCE(tc.totalClasses, 1) AS resAbsencePercent,
            COALESCE(da.disrespectfulAbsences, 0) * 100.0 / COALESCE(tc.totalClasses, 1) AS absencePercent
        FROM Students s
        CROSS JOIN Subjects sub
        LEFT JOIN RespectfulAbsences ra ON s.id = ra.studentId AND sub.id = ra.subjectId
        LEFT JOIN DisrespectfulAbsences da ON s.id = da.studentId AND sub.id = da.subjectId
        LEFT JOIN TotalAbsences ta ON s.id = ta.studentId AND sub.id = ta.subjectId
        LEFT JOIN TotalClasses tc ON sub.id = tc.subjectId
        ORDER BY s.id, sub.id;

    """
    )
    fun getStatisticsMM(): Flow<List<StatisticsMM>>

}