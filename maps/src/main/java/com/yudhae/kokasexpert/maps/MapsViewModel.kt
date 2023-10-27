package com.yudhae.kokasexpert.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yudhae.kokasexpert.core.domain.usecase.KokasUseCase

class MapsViewModel(kokasUseCase: KokasUseCase) : ViewModel() {
    val kokas = kokasUseCase.getAllKokas().asLiveData()
}