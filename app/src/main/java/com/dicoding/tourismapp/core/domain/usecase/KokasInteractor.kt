package com.dicoding.tourismapp.core.domain.usecase

import com.dicoding.tourismapp.core.domain.model.Kokas
import com.dicoding.tourismapp.core.domain.repository.IKokasRepository

class KokasInteractor(private val tourismRepository: IKokasRepository): KokasUseCase {

    override fun getAllTourism() = tourismRepository.getAllTourism()

    override fun getFavoriteTourism() = tourismRepository.getFavoriteTourism()

    override fun setFavoriteTourism(kokas: Kokas, state: Boolean) = tourismRepository.setFavoriteTourism(kokas, state)
}