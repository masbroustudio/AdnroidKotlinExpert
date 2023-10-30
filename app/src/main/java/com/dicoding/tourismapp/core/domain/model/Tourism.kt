package com.dicoding.tourismapp.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tourism(
    val id: String,
    val title: String,
    val description: String,
    val image: String,
    val isFavorite: Boolean
) : Parcelable
