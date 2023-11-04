package com.dicoding.tourismapp.core.data.source.remote

import com.dicoding.tourismapp.core.data.source.remote.network.ApiResponse
import com.dicoding.tourismapp.core.data.source.remote.network.ApiService
import com.dicoding.tourismapp.core.data.source.remote.response.TourismResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllTourism(): Flow<ApiResponse<List<TourismResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<TourismResponse>>>()


        return flow{
            try {
                val response = apiService.getList()
                val dataArray = response.places
                if(dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.places))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

//        client.subscribeOn(Schedulers.computation())
//            .observeOn(AndroidSchedulers.mainThread())
//            .take(1)
//            .subscribe({response ->
//                val dataArray = response.places
//                resultData.onNext(if(dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)
//            }, {error ->
//                resultData.onNext(ApiResponse.Error(error.message.toString()))
//            })

//        client.enqueue(object: Callback<ListTourismResponse> {
//            override fun onResponse(
//                call: Call<ListTourismResponse>,
//                response: Response<ListTourismResponse>
//            ) {
//                val dataArray = response.body()?.places
//                resultData.value = if(dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
//            }
//
//            override fun onFailure(call: Call<ListTourismResponse>, t: Throwable) {
//                resultData.value = ApiResponse.Error(t.message.toString())
//                Log.e("RemoteDataSource", t.message.toString())
//            }
//        })
//        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}

