package com.yudhae.kokassubexpert01.detail

import androidx.lifecycle.ViewModel
import com.kokassubexpert01.core.domain.model.Kokas
import com.kokassubexpert01.core.domain.usecase.KokasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailKokasViewModel @Inject constructor(private val kokasUseCase: KokasUseCase) :
    ViewModel() {
    fun setFavoriteKokas(kokas: Kokas, newStatus: Boolean) =
        kokasUseCase.setFavoriteKokas(kokas, newStatus)
}
