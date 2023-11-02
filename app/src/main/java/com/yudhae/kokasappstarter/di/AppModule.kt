package com.yudhae.kokasappstarter.di

import com.yudhae.kokasappstarter.core.domain.usecase.KokasInteractor
import com.yudhae.kokasappstarter.core.domain.usecase.KokasUseCase
import com.yudhae.kokasappstarter.detail.DetailKokasViewModel
import com.yudhae.kokasappstarter.favorite.FavoriteViewModel
import com.yudhae.kokasappstarter.home.HomeViewModel
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