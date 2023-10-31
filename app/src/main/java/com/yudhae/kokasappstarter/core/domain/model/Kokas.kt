package com.yudhae.kokasappstarter.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Kokas(
    val id: String,
    val title: String,
    val description: String,
    val image: String,
    val isFavorite: Boolean
) : Parcelable
