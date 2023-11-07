package com.kokassubexpert01.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Kokas(
    val kokasId: String,
    val title: String,
    val ingredients: String,
    val description: String,
    val image: String,
    val isFavorite: Boolean,
) : Parcelable
