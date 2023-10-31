package com.yudhae.kokasappstarter.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yudhae.kokasappstarter.core.domain.usecase.KokasUseCase

class FavoriteViewModel(kokasUseCase: KokasUseCase) : ViewModel() {
    val favoriteTourism = kokasUseCase.getFavoriteTourism().asLiveData()
}
