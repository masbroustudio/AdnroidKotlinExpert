package com.yudhae.kokasappstarter.core.domain.usecase

import com.yudhae.kokasappstarter.core.data.Resource
import com.yudhae.kokasappstarter.core.domain.model.Kokas
import kotlinx.coroutines.flow.Flow

interface KokasUseCase {
    fun getAllTourism(): Flow<Resource<List<Kokas>>>
    fun getFavoriteTourism(): Flow<List<Kokas>>
    fun setFavoriteTourism(kokas: Kokas, state: Boolean)
}