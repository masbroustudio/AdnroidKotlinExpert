package com.yudhae.kokassubexpert01.core.di.hilt

import com.yudhae.kokassubexpert01.core.data.TourismRepository
import com.yudhae.kokassubexpert01.core.domain.repository.ITourismRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(tourismRepository: TourismRepository): ITourismRepository
}