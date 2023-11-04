package com.yudhae.kokasappstarter.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Kokas(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    var ingradients: String,
    val isFavorite: Boolean
) : Parcelable
