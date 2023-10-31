package com.yudhae.kokasappstarter.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.yudhae.kokasappstarter.core.data.source.local.LocalDataSource
import com.yudhae.kokasappstarter.core.data.source.remote.RemoteDataSource
import com.yudhae.kokasappstarter.core.data.source.remote.network.ApiResponse
import com.yudhae.kokasappstarter.core.data.source.remote.response.KokasResponse
import com.yudhae.kokasappstarter.core.domain.model.Kokas
import com.yudhae.kokasappstarter.core.domain.repository.IKokasRepository
import com.yudhae.kokasappstarter.core.utils.AppExecutors
import com.yudhae.kokasappstarter.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class KokasRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IKokasRepository {

    companion object {
        @Volatile
        private var instance: KokasRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): KokasRepository =
            instance ?: synchronized(this) {
                instance ?: KokasRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllTourism(): Flow<Resource<List<Kokas>>> =
        object : NetworkBoundResource<List<Kokas>, List<KokasResponse>>() {
            override fun loadFromDB(): Flow<List<Kokas>> {
                return localDataSource.getAllTourism().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Kokas>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<KokasResponse>>> =
                remoteDataSource.getAllTourism()

            override suspend fun saveCallResult(data: List<KokasResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertTourism(tourismList)
            }
        }.asFlow()

    override fun getFavoriteTourism(): Flow<List<Kokas>> {
        return localDataSource.getFavoriteTourism().map { DataMapper.mapEntitiesToDomain(it) }

    }

    override fun setFavoriteTourism(kokas: Kokas, state: Boolean) {
        val kokasEntity = DataMapper.mapDomainToEntity(kokas)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTourism(kokasEntity, state) }
    }
}

