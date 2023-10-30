package com.yudhae.kokasappstarter.core.di

import android.content.Context
import com.yudhae.kokasappstarter.core.data.KokasRepository
import com.yudhae.kokasappstarter.core.data.source.local.LocalDataSource
import com.yudhae.kokasappstarter.core.data.source.local.room.KokasDatabase
import com.yudhae.kokasappstarter.core.data.source.remote.RemoteDataSource
import com.yudhae.kokasappstarter.core.data.source.remote.network.ApiConfig
import com.yudhae.kokasappstarter.core.domain.repository.IKokasRepository
import com.yudhae.kokasappstarter.core.domain.usecase.KokasInteractor
import com.yudhae.kokasappstarter.core.domain.usecase.KokasUseCase
import com.yudhae.kokasappstarter.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): IKokasRepository {
        val database = KokasDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.tourismDao())
        val appExecutors = AppExecutors()

        return KokasRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideTourismUseCase(context: Context): KokasUseCase {
        val repository = provideRepository(context)
        return KokasInteractor(repository)
    }
}
