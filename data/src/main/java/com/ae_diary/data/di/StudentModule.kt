package com.ae_diary.data.di

import com.ae_diary.data.repository.StudentRepositoryImpl
import com.ae_diary.domain.repository.StudentRepository
import com.ae_diary.domain.use_case.student.util.StudentUseCases
import com.ae_diary.domain.use_case.student.util.StudentUseCasesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StudentModule {

    @Singleton
    @Binds
    abstract fun bindStudentRepository(studentRepositoryImpl: StudentRepositoryImpl): StudentRepository

    @Binds
    abstract fun bindStudentUseCases(studentUseCasesImpl: StudentUseCasesImpl): StudentUseCases

}