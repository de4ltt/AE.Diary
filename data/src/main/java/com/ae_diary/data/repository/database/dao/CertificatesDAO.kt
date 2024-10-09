package com.ae_diary.data.repository.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ae_diary.data.model.Certificates
import kotlinx.coroutines.flow.Flow

@Dao
interface CertificatesDAO {

    @Insert
    fun addCertificate(certificate: Certificates)

    @Query("SELECT * FROM certificates")
    fun getAllCertificates(): Flow<List<Certificates>>

    @Delete
    fun deleteCertificate(certificate: Certificates)

}