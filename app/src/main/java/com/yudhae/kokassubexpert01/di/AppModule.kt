package com.yudhae.kokassubexpert01.di

import com.kokassubexpert01.core.domain.usecase.KokasInteractor
import com.kokassubexpert01.core.domain.usecase.KokasUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    @ViewModelScoped
    abstract fun provideKokasUseCase(tourismInteractor: KokasInteractor): KokasUseCase
}