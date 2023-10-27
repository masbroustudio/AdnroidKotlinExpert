package com.yudhae.kokasexpert.core.domain.usecase

import com.yudhae.kokasexpert.core.domain.model.Kokas
import com.yudhae.kokasexpert.core.domain.repository.IKokasRepository

class KokasInteractor(private val kokasRepository: IKokasRepository): KokasUseCase {

    override fun getAllKokas() = kokasRepository.getAllKokas()

    override fun getFavoriteKokas() = kokasRepository.getFavoriteKokas()

    override fun setFavoriteKokas(kokas: Kokas, state: Boolean) = kokasRepository.setFavoriteKokas(kokas, state)
}