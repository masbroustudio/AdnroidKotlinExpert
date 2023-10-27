package com.yudhae.kokasexpert.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Kokas(
    val kokasId: String,
    val name: String,
    val description: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val like: Int,
    val image: String,
    val isFavorite: Boolean
) : Parcelable