package com.yudhae.kokasexpert.maps.di

import com.yudhae.kokasexpert.maps.MapsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mapsModule = module {
    viewModel { MapsViewModel(get()) }
}