package com.kokassubexpert01.core.data.source.remote.response
import com.google.gson.annotations.SerializedName
import com.kokassubexpert01.core.data.source.remote.response.TourismResponse

data class ListTourismResponse(

    @field:SerializedName("kokas")
    val kokas: List<TourismResponse>,

    @field:SerializedName("count")
    val count: Int,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)