package com.yudhae.kokasexpert.core.data.source.remote.network

import com.yudhae.kokasexpert.core.data.source.remote.response.ListKokasResponse
import retrofit2.http.GET

interface ApiService {
    @GET("list")
    suspend fun getList(): ListKokasResponse
}
