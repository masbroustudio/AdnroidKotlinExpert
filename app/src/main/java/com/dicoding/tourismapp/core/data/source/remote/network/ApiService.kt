package com.dicoding.tourismapp.core.data.source.remote.network

import com.dicoding.tourismapp.core.data.source.remote.response.ListTourismResponse
import retrofit2.Call
import retrofit2.http.GET

// TODO : Selanjutnya buat sebuah interface, list merupakan endpoint dari url API

interface ApiService {
    @GET("list")
    fun getList(): Call<ListTourismResponse>
}