package com.yudhae.kokasappstarter.favorite

import androidx.lifecycle.ViewModel
import com.yudhae.kokasappstarter.core.domain.usecase.KokasUseCase

class FavoriteViewModel(kokasUseCase: KokasUseCase) : ViewModel() {
    val favoriteTourism = kokasUseCase.getFavoriteTourism()
}
