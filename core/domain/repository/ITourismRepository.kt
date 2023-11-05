package com.yudhae.kokassubexpert01.core.domain.repository

import androidx.lifecycle.LiveData
import com.yudhae.kokassubexpert01.core.data.Resource
import com.yudhae.kokassubexpert01.core.domain.model.Tourism
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

interface ITourismRepository {
    fun getAllTourism(): Flow<Resource<List<Tourism>>>
    fun getFavoriteTourism(): Flow<List<Tourism>>
    fun setFavoriteTourism(tourism: Tourism, state: Boolean)
}