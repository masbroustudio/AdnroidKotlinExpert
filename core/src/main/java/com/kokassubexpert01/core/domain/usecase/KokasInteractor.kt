package com.kokassubexpert01.core.domain.usecase

import com.kokassubexpert01.core.data.Resource
import com.kokassubexpert01.core.domain.model.Kokas
import com.kokassubexpert01.core.domain.repository.IKokasRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class KokasInteractor @Inject constructor(private val tourismRepository: IKokasRepository) :
    KokasUseCase {
    override fun getAllKokas(): Flow<Resource<List<Kokas>>> = tourismRepository.getAllKokas()

    override fun getFavoriteKokas(): Flow<List<Kokas>> = tourismRepository.getFavoriteKokas()

    override fun setFavoriteKokas(kokas: Kokas, state: Boolean) = tourismRepository.setFavoriteKokas(kokas, state)
}