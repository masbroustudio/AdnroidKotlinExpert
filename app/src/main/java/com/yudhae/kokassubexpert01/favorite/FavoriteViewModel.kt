package com.yudhae.kokassubexpert01.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kokassubexpert01.core.domain.usecase.KokasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(kokasUseCase: KokasUseCase) : ViewModel() {
    val favoriteTourism = kokasUseCase.getFavoriteKokas().asLiveData()
}
