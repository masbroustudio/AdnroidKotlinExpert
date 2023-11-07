package com.yudhae.kokassubexpert01.core.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class TourismResponse(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("address")
    val address: String,

    @field:SerializedName("like")
    val like: Int,

    @field:SerializedName("latitude")
    val latitude: Double,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("longitude")
    val longitude: Double
)
