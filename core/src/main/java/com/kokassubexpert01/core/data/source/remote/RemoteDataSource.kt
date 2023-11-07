package com.kokassubexpert01.core.data.source.remote

import com.kokassubexpert01.core.data.source.remote.network.ApiResponse
import com.kokassubexpert01.core.data.source.remote.network.ApiService
import com.kokassubexpert01.core.data.source.remote.response.KokasResponse
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllKokas(): Flow<ApiResponse<List<KokasResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<KokasResponse>>>()


        return flow{
            try {
                val response = apiService.getList()
                val dataArray = response.kokas
                if(dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.kokas))
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

//        client.enqueue(object: Callback<ListKokasResponse> {
//            override fun onResponse(
//                call: Call<ListKokasResponse>,
//                response: Response<ListKokasResponse>
//            ) {
//                val dataArray = response.body()?.places
//                resultData.value = if(dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
//            }
//
//            override fun onFailure(call: Call<ListKokasResponse>, t: Throwable) {
//                resultData.value = ApiResponse.Error(t.message.toString())
//                Log.e("RemoteDataSource", t.message.toString())
//            }
//        })
//        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}
