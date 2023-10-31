package com.yudhae.kokasappstarter.detail

import androidx.lifecycle.ViewModel
import com.yudhae.kokasappstarter.core.domain.model.Kokas
import com.yudhae.kokasappstarter.core.domain.usecase.KokasUseCase

class DetailKokasViewModel(private val kokasUseCase: KokasUseCase) : ViewModel() {
    fun setFavoriteTourism(kokas: Kokas, newStatus: Boolean) =
        kokasUseCase.setFavoriteTourism(kokas, newStatus)
}
