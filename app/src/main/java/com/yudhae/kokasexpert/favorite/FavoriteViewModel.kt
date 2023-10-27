package com.yudhae.kokasexpert.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yudhae.kokasexpert.core.domain.usecase.KokasUseCase

class FavoriteViewModel(kokasUseCase: KokasUseCase) : ViewModel() {
    val favoriteKokas = kokasUseCase.getFavoriteKokas().asLiveData()
}

