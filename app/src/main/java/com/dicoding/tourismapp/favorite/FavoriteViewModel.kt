package com.dicoding.tourismapp.favorite

import androidx.lifecycle.ViewModel
import com.dicoding.tourismapp.core.domain.usecase.KokasUseCase

class FavoriteViewModel(kokasUseCase: KokasUseCase) : ViewModel() {
    val favoriteTourism = kokasUseCase.getFavoriteTourism()
}
