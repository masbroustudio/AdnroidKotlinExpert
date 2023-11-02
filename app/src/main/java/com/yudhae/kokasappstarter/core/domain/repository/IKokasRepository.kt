package com.yudhae.kokasappstarter.core.domain.repository

import com.yudhae.kokasappstarter.core.data.Resource
import com.yudhae.kokasappstarter.core.domain.model.Kokas
import io.reactivex.Flowable

interface IKokasRepository {
    fun getAllTourism(): Flowable<Resource<List<Kokas>>>

    fun getFavoriteTourism(): Flowable<List<Kokas>>

    fun setFavoriteTourism(kokas: Kokas, state: Boolean)
}