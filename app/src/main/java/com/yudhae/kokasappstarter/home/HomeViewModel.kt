package com.yudhae.kokasappstarter.home

import androidx.lifecycle.ViewModel
import com.yudhae.kokasappstarter.core.domain.usecase.KokasUseCase

class HomeViewModel(kokasUseCase: KokasUseCase) : ViewModel() {
    val kokas = kokasUseCase.getAllTourism()
}

