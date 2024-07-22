package com.example.deathnote.domain.model

data class CertificateDomain(
    val id: Int,
    val studentId: Int,
    val start: String,
    val end: String
): DomainModel