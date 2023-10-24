package com.dicoding.tourismapp.home

import androidx.lifecycle.ViewModel
import com.dicoding.tourismapp.core.data.TourismRepository
import com.dicoding.tourismapp.core.domain.usecase.TourismUseCase

// TODO : ganti constructor pada setiap ViewModel yang sebelumnya Repository menjadi Use Case

class HomeViewModel(tourismUseCase: TourismUseCase) : ViewModel() {
    val tourism = tourismUseCase.getAllTourism()
}
