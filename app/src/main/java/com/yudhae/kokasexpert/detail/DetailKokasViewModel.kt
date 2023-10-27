package com.yudhae.kokasexpert.detail

import androidx.lifecycle.ViewModel
import com.yudhae.kokasexpert.core.domain.model.Kokas
import com.yudhae.kokasexpert.core.domain.usecase.KokasUseCase

class DetailKokasViewModel(private val kokasUseCase: KokasUseCase) : ViewModel() {
    fun setFavoriteKokas(kokas: Kokas, newStatus:Boolean) =
        kokasUseCase.setFavoriteKokas(kokas, newStatus)
}

