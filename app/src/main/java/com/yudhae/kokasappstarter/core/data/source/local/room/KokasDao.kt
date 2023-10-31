package com.yudhae.kokasappstarter.core.data.source.local.room

import androidx.room.*
import com.yudhae.kokasappstarter.core.data.source.local.entity.KokasEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface KokasDao {

    @Query("SELECT * FROM kokas")
    fun getAllTourism(): Flow<List<KokasEntity>>

    @Query("SELECT * FROM kokas where isFavorite = 1")
    fun getFavoriteTourism(): Flow<List<KokasEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTourism(kokas: List<KokasEntity>)

    @Update
    fun updateFavoriteTourism(kokas: KokasEntity)
}
