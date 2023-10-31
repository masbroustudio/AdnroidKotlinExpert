package com.yudhae.kokasappstarter.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yudhae.kokasappstarter.core.data.source.remote.network.ApiResponse
import com.yudhae.kokasappstarter.core.data.source.remote.network.ApiService
import com.yudhae.kokasappstarter.core.data.source.remote.response.KokasResponse
import com.yudhae.kokasappstarter.core.data.source.remote.response.ListKokasResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getAllTourism(): LiveData<ApiResponse<List<KokasResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<KokasResponse>>>()

        //get data from local json
        val client = apiService.getList()


        client.enqueue(object : Callback<ListKokasResponse> {
            override fun onResponse(
                call: Call<ListKokasResponse>,
                response: Response<ListKokasResponse>
            ) {
                val dataArray = response.body()
                resultData.value =
                    if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<ListKokasResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultData
    }
}

