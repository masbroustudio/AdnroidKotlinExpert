package com.dicoding.tourismapp.favorite

import androidx.lifecycle.ViewModel
import com.dicoding.tourismapp.core.data.TourismRepository
import com.dicoding.tourismapp.core.domain.usecase.TourismUseCase

// TODO : ganti constructor pada setiap ViewModel yang sebelumnya Repository menjadi Use Case

class FavoriteViewModel(tourismUseCase: TourismUseCase) : ViewModel() {
    val favoriteTourism = tourismUseCase.getFavoriteTourism()
}