package com.kokassubexpert01.core.data

import com.kokassubexpert01.core.data.source.remote.network.ApiResponse
import com.kokassubexpert01.core.data.source.local.LocalDataSource
import com.kokassubexpert01.core.data.source.remote.RemoteDataSource
import com.kokassubexpert01.core.data.source.remote.response.KokasResponse
import com.kokassubexpert01.core.domain.model.Kokas
import com.kokassubexpert01.core.domain.repository.IKokasRepository
import com.kokassubexpert01.core.utils.AppExecutors
import com.kokassubexpert01.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KokasRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IKokasRepository {
    override fun getAllKokas(): Flow<Resource<List<Kokas>>> =
        object : NetworkBoundResource<List<Kokas>, List<KokasResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Kokas>> {
                return localDataSource.getAllKokas().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Kokas>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<KokasResponse>>> =
                remoteDataSource.getAllKokas()

            override suspend fun saveCallResult(data: List<KokasResponse>) {
                val kokasList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertKokas(kokasList)
            }
        }.asFlow()

    override fun getFavoriteKokas(): Flow<List<Kokas>> {
        return localDataSource.getFavoriteKokas().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteKokas(kokas: Kokas, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(kokas)
        appExecutors.diskIO().execute { localDataSource.setFavoriteKokas(tourismEntity, state) }
    }
}

