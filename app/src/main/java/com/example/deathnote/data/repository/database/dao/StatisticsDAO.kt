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
        SELECT subjectId, COUNT(date) AS count
        FROM Timetables
        WHERE isDismissed = 0
        GROUP BY subjectId
    ),
    AllResAbsences AS (
        SELECT studentId, subjectId, COUNT(*) AS count
        FROM Absences
        WHERE respectful = 1
        GROUP BY studentId, subjectId
    ),
    AllDisresAbsences AS (
        SELECT studentId, subjectId, COUNT(*) AS count
        FROM Absences
        WHERE respectful = 0
        GROUP BY studentId, subjectId
    ),
    AllAbsences AS (
        SELECT studentId, subjectId, COUNT(*) AS count
        FROM Absences
        GROUP BY studentId, subjectId
    )

    SELECT
        s.id AS subjectId,
        st.id AS studentId,
        COALESCE(ad.count, 0) AS absence,
        COALESCE(aa.count, 0) AS resAbsence,
        COALESCE((a.count / NULLIF(ac.count, 0)), 0) AS absencePercent
    FROM Students st
    JOIN Subjects s
    LEFT JOIN AllClasses ac ON s.id = ac.subjectId
    LEFT JOIN AllAbsences a ON st.id = a.studentId AND s.id = a.subjectId
    LEFT JOIN AllResAbsences aa ON st.id = aa.studentId AND s.id = aa.subjectId
    LEFT JOIN AllDisresAbsences ad ON st.id = ad.studentId AND s.id = ad.subjectId
    GROUP BY s.id
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
        SELECT studentId, subjectId, COUNT(*) AS totalAbsences
        FROM Absences
        GROUP BY studentId, subjectId
    )

    SELECT
        s.id AS subjectId,
        st.id AS studentId,
        COALESCE(ra.respectfulAbsences, 0) AS resAbsence,
        COALESCE(da.disrespectfulAbsences, 0) AS absence,
        COALESCE((da.disrespectfulAbsences / NULLIF(ta.totalAbsences, 0)), 0) AS absencePercent
    FROM Students st
    JOIN Subjects s
    LEFT JOIN TotalClasses tc ON s.id = tc.subjectId
    LEFT JOIN RespectfulAbsences ra ON st.id = ra.studentId AND s.id = ra.subjectId
    LEFT JOIN DisrespectfulAbsences da ON st.id = da.studentId AND s.id = da.subjectId
    LEFT JOIN TotalAbsences ta ON st.id = ta.studentId AND s.id = ta.subjectId
    GROUP BY st.id
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
        GROUP BY studentId, subjectId
    ),
    TotalAbsences AS (
        SELECT studentId, subjectId, COUNT(*) AS totalAbsences
        FROM Absences
        GROUP BY studentId, subjectId
    )

    SELECT
        s.id AS subjectId,
        COALESCE(((tc.totalClasses - COALESCE(ta.totalAbsences, 0)) / NULLIF(tc.totalClasses, 0)), 100) AS presencePercent,
        COALESCE((ra.respectfulAbsences / NULLIF(ta.totalAbsences, 0)), 100) AS resAbsencePercent,
        COALESCE((da.disrespectfulAbsences / NULLIF(ta.totalAbsences, 0)), 0) AS absencePercent
    FROM Students st
    JOIN Subjects s
    LEFT JOIN TotalClasses tc ON s.id = tc.subjectId
    LEFT JOIN RespectfulAbsences ra ON st.id = ra.studentId AND s.id = ra.subjectId
    LEFT JOIN DisrespectfulAbsences da ON st.id = da.studentId AND s.id = da.subjectId
    LEFT JOIN TotalAbsences ta ON st.id = ta.studentId AND s.id = ta.subjectId
    GROUP BY st.id
    """
    )
    fun getStatisticsMM(): Flow<List<StatisticsMM>>



}