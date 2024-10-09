package com.ae_diary.data.di

import com.ae_diary.domain.use_case.main_screen.util.MainScreenUseCases
import com.ae_diary.domain.use_case.main_screen.util.MainScreenUseCasesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MainScreenModule {

    @Binds
    @Singleton
    abstract fun bindMainScreenUseCases(
        mainScreenUseCasesImpl: MainScreenUseCasesImpl
    ): MainScreenUseCases

}