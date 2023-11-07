package com.yudhae.kokassubexpert01.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kokassubexpert01.core.domain.usecase.KokasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(kokasUseCase: KokasUseCase) : ViewModel() {
    val kokas = kokasUseCase.getAllKokas().asLiveData()
}
