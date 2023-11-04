package com.yudhae.kokasappstarter.core.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class ListKokasResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("kokas")
    val kokas: List<KokasResponse>
)


//data class ListKokasResponse(
////    @field:SerializedName("kokas")
//    val kokas: List<KokasResponse>
//)
//
////class ListKokasResponse : ArrayList<KokasResponse>()