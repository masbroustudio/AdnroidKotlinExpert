package com.yudhae.kokasexpert.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yudhae.kokasexpert.core.domain.usecase.KokasUseCase

class HomeViewModel(kokasUseCase: KokasUseCase) : ViewModel() {
    val kokas = kokasUseCase.getAllKokas().asLiveData()
}

