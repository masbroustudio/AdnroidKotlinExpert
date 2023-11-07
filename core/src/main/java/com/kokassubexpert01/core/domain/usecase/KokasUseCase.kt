package com.kokassubexpert01.core.domain.usecase

import com.kokassubexpert01.core.data.Resource
import com.kokassubexpert01.core.domain.model.Kokas
import kotlinx.coroutines.flow.Flow

interface KokasUseCase {
    fun getAllKokas(): Flow<Resource<List<Kokas>>>
    fun getFavoriteKokas(): Flow<List<Kokas>>
    fun setFavoriteKokas(kokas: Kokas, state: Boolean)
}