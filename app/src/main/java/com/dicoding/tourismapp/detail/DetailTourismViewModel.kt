package com.dicoding.tourismapp.detail

import androidx.lifecycle.ViewModel
import com.dicoding.tourismapp.core.data.TourismRepository
import com.dicoding.tourismapp.core.data.source.local.entity.TourismEntity
import com.dicoding.tourismapp.core.domain.model.Tourism
import com.dicoding.tourismapp.core.domain.usecase.TourismUseCase

// TODO : ganti constructor pada setiap ViewModel yang sebelumnya Repository menjadi Use Case

class DetailTourismViewModel(private val tourismUseCase: TourismUseCase) : ViewModel() {

    //    fun setFavoriteTourism(tourism: TourismEntity, newStatus:Boolean) = tourismRepository.setFavoriteTourism(tourism, newStatus)

    // TODO : ubah juga tipe data yang sebelumnya menggunakan TourismEntity menjadi Tourism
//    fun setFavoriteTourism(tourism: Tourism, newStatus: Boolean) =
//        tourismRepository.setFavoriteTourism(tourism, newStatus)

    // TODO : ganti constructor pada setiap ViewModel yang sebelumnya Repository menjadi Use Case
    fun setFavoriteTourism(tourism: Tourism, newStatus:Boolean) =
        tourismUseCase.setFavoriteTourism(tourism, newStatus)
}
