package com.kokassubexpert01.core.data.source.remote.network

import com.kokassubexpert01.core.data.source.remote.response.ListKokasResponse
import retrofit2.http.GET

interface ApiService {
    @GET("537d5e89-8311-4c7c-a4c6-e99d7a901716")
    suspend fun getList(): ListKokasResponse
}