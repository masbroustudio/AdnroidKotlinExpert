package com.dicoding.tourismapp.core.data

import androidx.lifecycle.LiveData
import com.dicoding.tourismapp.core.data.source.remote.network.ApiResponse
import com.dicoding.tourismapp.core.data.source.local.LocalDataSource
import com.dicoding.tourismapp.core.data.source.local.entity.TourismEntity
import com.dicoding.tourismapp.core.data.source.remote.RemoteDataSource
import com.dicoding.tourismapp.core.data.source.remote.response.TourismResponse
import com.dicoding.tourismapp.core.utils.AppExecutors
import com.dicoding.tourismapp.core.utils.DataMapper

class TourismRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) {

    companion object {
        @Volatile
        private var instance: TourismRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): TourismRepository =
            instance ?: synchronized(this) {
                instance ?: TourismRepository(remoteData, localData, appExecutors)
            }
    }

    // TODO : Resource yang berfungsi untuk membungkus data dan statusnya
    fun getAllTourism(): LiveData<Resource<List<TourismEntity>>> =
        object : NetworkBoundResource<List<TourismEntity>, List<TourismResponse>>(appExecutors) {
            // TODO : Saat aplikasi dijalankan, sistem akan mengecek data pada local menggunakan method
            override fun loadFromDB(): LiveData<List<TourismEntity>> {
                return localDataSource.getAllTourism()
            }

            // TODO : menentukan kapan bisa mengambil data dari remote pada method
            override fun shouldFetch(data: List<TourismEntity>?): Boolean =
                data == null || data.isEmpty()

            // TODO : Ketika data pada local kosong, maka aplikasi akan mengambil data dari remote
            override fun createCall(): LiveData<ApiResponse<List<TourismResponse>>> =
                remoteDataSource.getAllTourism()

            // TODO : menyimpannya ke dalam local menggunakan method
            override fun saveCallResult(data: List<TourismResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertTourism(tourismList)
            }
        }.asLiveData()

    fun getFavoriteTourism(): LiveData<List<TourismEntity>> {
        return localDataSource.getFavoriteTourism()
    }

    fun setFavoriteTourism(tourism: TourismEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteTourism(tourism, state) }
    }
}

