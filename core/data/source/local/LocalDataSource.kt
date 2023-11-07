package com.yudhae.kokassubexpert01.core.data.source.local

import androidx.lifecycle.LiveData
import com.yudhae.kokassubexpert01.core.data.source.local.entity.TourismEntity
import com.yudhae.kokassubexpert01.core.data.source.local.room.TourismDao
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val tourismDao: TourismDao) {
    fun getAllTourism(): Flow<List<TourismEntity>> = tourismDao.getAllTourism()

    fun getFavoriteTourism(): Flow<List<TourismEntity>> = tourismDao.getFavoriteTourism()

    suspend fun insertTourism(tourismList: List<TourismEntity>) =
        tourismDao.insertTourism(tourismList)

    fun setFavoriteTourism(tourism: TourismEntity, newState: Boolean) {
        tourism.isFavorite = newState
        tourismDao.updateFavoriteTourism(tourism)
    }
}