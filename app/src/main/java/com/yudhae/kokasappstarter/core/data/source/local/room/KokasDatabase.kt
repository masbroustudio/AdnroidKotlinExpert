package com.yudhae.kokasappstarter.core.data.source.local.room

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.yudhae.kokasappstarter.core.data.source.local.entity.KokasEntity

@Database(entities = [KokasEntity::class], version = 1, exportSchema = false)
abstract class KokasDatabase : RoomDatabase() {

    abstract fun tourismDao(): KokasDao

    companion object {
        @Volatile
        private var INSTANCE: KokasDatabase? = null

        fun getInstance(context: Context): KokasDatabase =
            INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                KokasDatabase::class.java,
                "Kokas.db"
            )
                .fallbackToDestructiveMigration()
                .build()
            INSTANCE = instance
            instance
        }
    }
}