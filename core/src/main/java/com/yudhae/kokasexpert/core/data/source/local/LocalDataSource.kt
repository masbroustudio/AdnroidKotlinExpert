package com.yudhae.kokasexpert.core.data.source.local

import com.yudhae.kokasexpert.core.data.source.local.entity.KokasEntity
import com.yudhae.kokasexpert.core.data.source.local.room.KokasDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val kokasDao: KokasDao) {

    fun getAllKokas(): Flow<List<KokasEntity>> = kokasDao.getAllKokas()

    fun getFavoriteKokas(): Flow<List<KokasEntity>> = kokasDao.getFavoriteKokas()

    suspend fun insertKokas(kokasList: List<KokasEntity>) = kokasDao.insertKokas(kokasList)

    fun setFavoriteKokas(kokas: KokasEntity, newState: Boolean) {
        kokas.isFavorite = newState
        kokasDao.updateFavoriteKokas(kokas)
    }
}