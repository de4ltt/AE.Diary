package com.ae_diary.domain.model

import com.ae_diary.domain.model.interfaces.DomainModel

data class CertificateDomain(
    val id: Int,
    val studentId: Int,
    val start: String,
    val end: String
): DomainModel