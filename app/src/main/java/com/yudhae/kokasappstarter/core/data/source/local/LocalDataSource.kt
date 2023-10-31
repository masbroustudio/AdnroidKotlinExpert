package com.yudhae.kokasappstarter.core.data.source.local

import com.yudhae.kokasappstarter.core.data.source.local.entity.KokasEntity
import com.yudhae.kokasappstarter.core.data.source.local.room.KokasDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val tourismDao: KokasDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(tourismDao: KokasDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(tourismDao)
            }
    }

    fun getAllTourism(): Flow<List<KokasEntity>> = tourismDao.getAllTourism()

    fun getFavoriteTourism(): Flow<List<KokasEntity>> = tourismDao.getFavoriteTourism()

    fun insertTourism(tourismList: List<KokasEntity>) =
        tourismDao.insertTourism(tourismList)

    fun setFavoriteTourism(kokas: KokasEntity, newState: Boolean) {
        kokas.isFavorite = newState
        tourismDao.updateFavoriteTourism(kokas)
    }
}