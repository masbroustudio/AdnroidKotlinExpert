package com.kokassubexpert01.core.di.hilt

import com.kokassubexpert01.core.data.KokasRepository
import com.kokassubexpert01.core.domain.repository.IKokasRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(kokasRepository: KokasRepository): IKokasRepository
}