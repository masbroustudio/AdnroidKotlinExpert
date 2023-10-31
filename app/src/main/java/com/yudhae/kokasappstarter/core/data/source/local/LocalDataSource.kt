package com.yudhae.kokasappstarter.core.data.source.local

import androidx.lifecycle.LiveData
import com.yudhae.kokasappstarter.core.data.source.local.entity.KokasEntity
import com.yudhae.kokasappstarter.core.data.source.local.room.KokasDao

class LocalDataSource private constructor(private val tourismDao: KokasDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(tourismDao: KokasDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(tourismDao)
            }
    }

    fun getAllTourism(): LiveData<List<KokasEntity>> = tourismDao.getAllTourism()

    fun getFavoriteTourism(): LiveData<List<KokasEntity>> = tourismDao.getFavoriteTourism()

    fun insertTourism(tourismList: List<KokasEntity>) = tourismDao.insertTourism(tourismList)

    fun setFavoriteTourism(kokas: KokasEntity, newState: Boolean) {
        kokas.isFavorite = newState
        tourismDao.updateFavoriteTourism(kokas)
    }
}