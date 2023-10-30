package com.dicoding.tourismapp.detail

import androidx.lifecycle.ViewModel
import com.dicoding.tourismapp.core.domain.model.Kokas
import com.dicoding.tourismapp.core.domain.usecase.KokasUseCase

class DetailKokasViewModel(private val kokasUseCase: KokasUseCase) : ViewModel() {
    fun setFavoriteTourism(kokas: Kokas, newStatus: Boolean) =
        kokasUseCase.setFavoriteTourism(kokas, newStatus)
}
