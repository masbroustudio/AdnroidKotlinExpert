package com.yudhae.kokassubexpert01.core.data.source.remote.network

import com.yudhae.kokassubexpert01.core.data.source.remote.response.ListTourismResponse
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("list")
    suspend fun getList(): ListTourismResponse
}