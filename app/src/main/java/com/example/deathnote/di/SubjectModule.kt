package com.example.deathnote.di

import com.example.deathnote.data.repository.SubjectRepositoryImpl
import com.example.deathnote.domain.repository.SubjectRepository
import com.example.deathnote.domain.use_case.subject.util.SubjectUseCases
import com.example.deathnote.domain.use_case.subject.util.SubjectUseCasesImpl
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