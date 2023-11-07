package com.kokassubexpert01.core.di.hilt

import android.content.Context
import androidx.room.Room
import com.kokassubexpert01.core.data.source.local.room.KokasDao
import com.kokassubexpert01.core.data.source.local.room.KokasDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): KokasDatabase = Room.databaseBuilder(
        context,
        KokasDatabase::class.java,
        "Kokas.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideKokasDao(database: KokasDatabase): KokasDao = database.kokasDao()
}