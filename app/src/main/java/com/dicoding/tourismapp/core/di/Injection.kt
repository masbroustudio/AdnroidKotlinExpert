package com.dicoding.tourismapp.core.di

import android.content.Context

import com.dicoding.tourismapp.core.data.source.local.LocalDataSource
import com.dicoding.tourismapp.core.data.source.local.room.KokasDatabase

import com.dicoding.tourismapp.core.data.KokasRepository
import com.dicoding.tourismapp.core.data.source.remote.RemoteDataSource
import com.dicoding.tourismapp.core.domain.repository.IKokasRepository
import com.dicoding.tourismapp.core.domain.usecase.KokasInteractor
import com.dicoding.tourismapp.core.domain.usecase.KokasUseCase
import com.dicoding.tourismapp.core.utils.AppExecutors
import com.dicoding.tourismapp.core.utils.JsonHelper

object Injection {
    private fun provideRepository(context: Context): IKokasRepository {
        val database = KokasDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.tourismDao())
        val appExecutors = AppExecutors()

        return KokasRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideTourismUseCase(context: Context): KokasUseCase {
        val repository = provideRepository(context)
        return KokasInteractor(repository)
    }
}
