package com.yudhae.kokasexpert.di

import com.yudhae.kokasexpert.core.domain.usecase.KokasInteractor
import com.yudhae.kokasexpert.core.domain.usecase.KokasUseCase
import com.yudhae.kokasexpert.detail.DetailKokasViewModel
import com.yudhae.kokasexpert.favorite.FavoriteViewModel
import com.yudhae.kokasexpert.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<KokasUseCase> { KokasInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailKokasViewModel(get()) }
}