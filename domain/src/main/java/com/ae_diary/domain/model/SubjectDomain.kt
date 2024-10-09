package com.ae_diary.domain.model

import com.ae_diary.domain.model.interfaces.DomainModel

data class SubjectDomain(
    val id: Int,
    val name: String,
    val type : String
): DomainModel