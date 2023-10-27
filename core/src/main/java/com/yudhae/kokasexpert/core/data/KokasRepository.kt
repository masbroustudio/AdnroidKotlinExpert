package com.yudhae.kokasexpert.core.data

import com.yudhae.kokasexpert.core.data.source.local.LocalDataSource
import com.yudhae.kokasexpert.core.data.source.remote.RemoteDataSource
import com.yudhae.kokasexpert.core.data.source.remote.network.ApiResponse
import com.yudhae.kokasexpert.core.data.source.remote.response.KokasResponse
import com.yudhae.kokasexpert.core.domain.model.Kokas
import com.yudhae.kokasexpert.core.domain.repository.IKokasRepository
import com.yudhae.kokasexpert.core.utils.AppExecutors
import com.yudhae.kokasexpert.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class KokasRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IKokasRepository {

    override fun getAllKokas(): Flow<Resource<List<Kokas>>> =
        object : NetworkBoundResource<List<Kokas>, List<KokasResponse>>() {
            override fun loadFromDB(): Flow<List<Kokas>> {
                return localDataSource.getAllKokas().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Kokas>?): Boolean =
//                data == null || data.isEmpty()
                 true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<KokasResponse>>> =
                remoteDataSource.getAllKokas()

            override suspend fun saveCallResult(data: List<KokasResponse>) {
                val kokasList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertKokas(kokasList)
            }
        }.asFlow()

    override fun getFavoriteKokas(): Flow<List<Kokas>> {
        return localDataSource.getFavoriteKokas().map {
           DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteKokas(kokas: Kokas, state: Boolean) {
        val kokasEntity = DataMapper.mapDomainToEntity(kokas)
        appExecutors.diskIO().execute { localDataSource.setFavoriteKokas(kokasEntity, state) }
    }
}

