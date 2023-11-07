package com.kokassubexpert01.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

import com.kokassubexpert01.core.data.source.local.entity.KokasEntity

@Database(entities = [KokasEntity::class], version = 1, exportSchema = false)
abstract class KokasDatabase : RoomDatabase() {
    abstract fun kokasDao(): KokasDao
}