package com.yudhae.kokasappstarter.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yudhae.kokasappstarter.core.data.source.local.entity.KokasEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface KokasDao {

    @Query("SELECT * FROM kokas")
    fun getAllTourism(): Flowable<List<KokasEntity>>

    @Query("SELECT * FROM kokas where isFavorite = 1")
    fun getFavoriteTourism(): Flowable<List<KokasEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTourism(kokas: List<KokasEntity>): Completable

    @Update
    fun updateFavoriteTourism(kokas: KokasEntity)
}
