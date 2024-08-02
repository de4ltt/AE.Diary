package com.example.data.di

import com.example.ae_diary.data.repository.SubjectRepositoryImpl
import com.example.ae_diary.domain.repository.SubjectRepository
import com.example.ae_diary.domain.use_case.subject.util.SubjectUseCases
import com.example.ae_diary.domain.use_case.subject.util.SubjectUseCasesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SubjectModule {

    @Binds
    abstract fun bindSubjectUseCases(subjectUseCasesImpl: SubjectUseCasesImpl): SubjectUseCases

    @Singleton
    @Binds
    abstract fun bindSubjectRepository(subjectRepositoryImpl: SubjectRepositoryImpl): SubjectRepository

}