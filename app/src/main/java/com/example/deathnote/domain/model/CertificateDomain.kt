package com.example.deathnote.domain.model

data class CertificateDomain(
    val id: Int? = null,
    val studentId: Int? = null,
    val start: String = "08:00",
    val end: String = "12:00",
): DomainModel