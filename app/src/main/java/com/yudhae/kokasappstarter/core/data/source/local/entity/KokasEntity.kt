package com.yudhae.kokasappstarter.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kokas")
data class KokasEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "ingredients")
    var ingredients: List<String?>,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
