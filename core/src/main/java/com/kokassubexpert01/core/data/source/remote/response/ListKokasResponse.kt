package com.kokassubexpert01.core.data.source.remote.response
import com.google.gson.annotations.SerializedName

data class ListKokasResponse(

    @field:SerializedName("kokas")
    val kokas: List<KokasResponse>,

    @field:SerializedName("count")
    val count: Int,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)