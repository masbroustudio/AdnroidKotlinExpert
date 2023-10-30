package com.dicoding.tourismapp.home

import androidx.lifecycle.ViewModel
import com.dicoding.tourismapp.core.domain.usecase.KokasUseCase

class HomeViewModel(kokasUseCase: KokasUseCase) : ViewModel() {
    val kokas = kokasUseCase.getAllTourism()
}

