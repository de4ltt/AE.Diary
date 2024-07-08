package com.example.deathnote.data.repository.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.deathnote.data.model.Certificates
import kotlinx.coroutines.flow.Flow

@Dao
interface CertificatesDAO {

    @Insert
    fun addCertificate(certificate: Certificates)

    @Query("SELECT * FROM certificates")
    fun getAllCertificates(): Flow<List<Certificates>>

    @Query("DELETE FROM certificates WHERE studentId = :id")
    fun deleteCertificatesByStudentId(id: Int)

    @Delete
    fun deleteCertificate(certificate: Certificates)

}