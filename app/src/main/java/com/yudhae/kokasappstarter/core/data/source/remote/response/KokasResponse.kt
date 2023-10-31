package com.yudhae.kokasappstarter.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class KokasResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("ingredients")
    val ingredients: List<String?>,

    @field:SerializedName("image")
    val image: String
)
