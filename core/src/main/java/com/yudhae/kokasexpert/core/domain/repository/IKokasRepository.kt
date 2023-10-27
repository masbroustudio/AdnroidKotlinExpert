package com.yudhae.kokasexpert.core.domain.repository

import com.yudhae.kokasexpert.core.data.Resource
import com.yudhae.kokasexpert.core.domain.model.Kokas
import kotlinx.coroutines.flow.Flow

interface IKokasRepository {

    fun getAllKokas(): Flow<Resource<List<Kokas>>>

    fun getFavoriteKokas(): Flow<List<Kokas>>

    fun setFavoriteKokas(kokas: Kokas, state: Boolean)

}