package com.example.deathnote.domain.model

import com.example.deathnote.domain.model.interfaces.DomainModel

data class CertificateDomain(
    val id: Int,
    val studentId: Int,
    val start: String,
    val end: String
): DomainModel