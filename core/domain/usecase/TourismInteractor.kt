package com.yudhae.kokassubexpert01.core.domain.usecase

import androidx.lifecycle.LiveData
import com.yudhae.kokassubexpert01.core.data.Resource
import com.yudhae.kokassubexpert01.core.domain.model.Tourism
import com.yudhae.kokassubexpert01.core.domain.repository.ITourismRepository
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TourismInteractor @Inject constructor(private val tourismRepository: ITourismRepository) : TourismUseCase {
    override fun getAllTourism(): Flow<Resource<List<Tourism>>> = tourismRepository.getAllTourism()

    override fun getFavoriteTourism(): Flow<List<Tourism>> = tourismRepository.getFavoriteTourism()

    override fun setFavoriteTourism(tourism: Tourism, state: Boolean) = tourismRepository.setFavoriteTourism(tourism, state)
}