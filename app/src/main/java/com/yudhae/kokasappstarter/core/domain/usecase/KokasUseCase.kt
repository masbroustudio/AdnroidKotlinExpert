package com.yudhae.kokasappstarter.core.domain.usecase

import androidx.lifecycle.LiveData
import com.yudhae.kokasappstarter.core.data.Resource
import com.yudhae.kokasappstarter.core.domain.model.Kokas

interface KokasUseCase {
    fun getAllTourism(): LiveData<Resource<List<Kokas>>>
    fun getFavoriteTourism(): LiveData<List<Kokas>>
    fun setFavoriteTourism(kokas: Kokas, state: Boolean)
}