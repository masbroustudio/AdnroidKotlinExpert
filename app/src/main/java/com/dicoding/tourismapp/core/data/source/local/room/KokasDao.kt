package com.dicoding.tourismapp.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dicoding.tourismapp.core.data.source.local.entity.KokasEntity

@Dao
interface KokasDao {

    @Query("SELECT * FROM kokas")
    fun getAllTourism(): LiveData<List<KokasEntity>>

    @Query("SELECT * FROM kokas where isFavorite = 1")
    fun getFavoriteTourism(): LiveData<List<KokasEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTourism(kokas: List<KokasEntity>)

    @Update
    fun updateFavoriteTourism(kokas: KokasEntity)
}
