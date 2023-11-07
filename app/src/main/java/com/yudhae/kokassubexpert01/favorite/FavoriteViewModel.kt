package com.yudhae.kokassubexpert01.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kokassubexpert01.core.domain.usecase.TourismUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(tourismUseCase: TourismUseCase) : ViewModel() {
    val favoriteTourism = tourismUseCase.getFavoriteTourism().asLiveData()
}

