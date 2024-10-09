package com.ae_diary.data.di

import com.ae_diary.data.repository.CertificateRepositoryImpl
import com.ae_diary.domain.repository.CertificateRepository
import com.ae_diary.domain.use_case.certificate.util.CertificateUseCases
import com.ae_diary.domain.use_case.certificate.util.CertificateUseCasesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CertificateModule {

    @Singleton
    @Binds
    abstract fun bindCertificateRepository(
        certificateRepositoryImpl: CertificateRepositoryImpl
    ): CertificateRepository

    @Binds
    abstract fun bindCertificateUseCases(
        certificateUseCasesImpl: CertificateUseCasesImpl
    ): CertificateUseCases
}