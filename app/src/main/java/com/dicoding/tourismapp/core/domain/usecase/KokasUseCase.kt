package com.dicoding.tourismapp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.dicoding.tourismapp.core.data.Resource
import com.dicoding.tourismapp.core.domain.model.Kokas

interface KokasUseCase {
    fun getAllTourism(): LiveData<Resource<List<Kokas>>>
    fun getFavoriteTourism(): LiveData<List<Kokas>>
    fun setFavoriteTourism(kokas: Kokas, state: Boolean)
}