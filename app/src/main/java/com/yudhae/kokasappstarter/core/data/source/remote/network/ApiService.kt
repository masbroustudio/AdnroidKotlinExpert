package com.yudhae.kokasappstarter.core.data.source.remote.network

import com.yudhae.kokasappstarter.core.data.source.remote.response.ListKokasResponse
import retrofit2.http.GET

interface ApiService {
    @GET("coffee/hot")
    suspend fun getList(): ListKokasResponse
}