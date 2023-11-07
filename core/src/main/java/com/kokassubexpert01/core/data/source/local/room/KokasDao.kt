package com.kokassubexpert01.core.data.source.local.room

import androidx.room.*
import com.kokassubexpert01.core.data.source.local.entity.KokasEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface KokasDao {

    @Query("SELECT * FROM kokas")
    fun getAllKokas(): Flow<List<KokasEntity>>

    @Query("SELECT * FROM kokas where isFavorite = 1")
    fun getFavoriteKokas(): Flow<List<KokasEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKokas(kokas: List<KokasEntity>)

    @Update
    fun updateFavoriteKokas(kokas: KokasEntity)
}
