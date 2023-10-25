package com.dicoding.tourismapp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.dicoding.tourismapp.core.data.Resource
import com.dicoding.tourismapp.core.domain.model.Tourism
import io.reactivex.Flowable

//interface TourismUseCase {
//    fun getAllTourism(): LiveData<Resource<List<Tourism>>>
//    fun getFavoriteTourism(): LiveData<List<Tourism>>
//    fun setFavoriteTourism(tourism: Tourism, state: Boolean)
//}

// TODO : 9
interface TourismUseCase {
    fun getAllTourism(): Flowable<Resource<List<Tourism>>>
    fun getFavoriteTourism(): Flowable<List<Tourism>>
    fun setFavoriteTourism(tourism: Tourism, state: Boolean)
}