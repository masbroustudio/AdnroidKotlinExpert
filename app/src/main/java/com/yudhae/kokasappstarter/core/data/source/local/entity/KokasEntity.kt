package com.yudhae.kokasappstarter.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "kokas")
data class KokasEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable
