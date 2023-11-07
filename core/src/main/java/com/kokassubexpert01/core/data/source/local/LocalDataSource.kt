package com.kokassubexpert01.core.data.source.local

import com.kokassubexpert01.core.data.source.local.entity.KokasEntity
import com.kokassubexpert01.core.data.source.local.room.KokasDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val kokasDao: KokasDao) {
    fun getAllKokas(): Flow<List<KokasEntity>> = kokasDao.getAllKokas()

    fun getFavoriteKokas(): Flow<List<KokasEntity>> = kokasDao.getFavoriteKokas()

    suspend fun insertKokas(kokasList: List<KokasEntity>) =
        kokasDao.insertKokas(kokasList)

    fun setFavoriteKokas(kokas: KokasEntity, newState: Boolean) {
        kokas.isFavorite = newState
        kokasDao.updateFavoriteKokas(kokas)
    }
}