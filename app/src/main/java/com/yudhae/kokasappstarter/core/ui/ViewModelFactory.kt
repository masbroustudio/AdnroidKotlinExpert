package com.yudhae.kokasappstarter.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yudhae.kokasappstarter.core.di.Injection
import com.yudhae.kokasappstarter.core.domain.usecase.KokasUseCase
import com.yudhae.kokasappstarter.detail.DetailKokasViewModel
import com.yudhae.kokasappstarter.favorite.FavoriteViewModel
import com.yudhae.kokasappstarter.home.HomeViewModel

class ViewModelFactory private constructor(private val kokasUseCase: KokasUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideTourismUseCase(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(kokasUseCase) as T
            }

            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(kokasUseCase) as T
            }

            modelClass.isAssignableFrom(DetailKokasViewModel::class.java) -> {
                DetailKokasViewModel(kokasUseCase) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}