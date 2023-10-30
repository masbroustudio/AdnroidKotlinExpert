package com.yudhae.kokasappstarter.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class KokasResponse(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("image")
    val image: String
)

