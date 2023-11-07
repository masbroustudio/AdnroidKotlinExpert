package com.kokassubexpert01.core.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class KokasResponse(

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("ingredients")
    val ingredients: String,

    @field:SerializedName("image")
    val image: String,

)
