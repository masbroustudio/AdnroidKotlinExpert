package com.yudhae.kokasappstarter.core.utils

import com.yudhae.kokasappstarter.core.data.source.local.entity.KokasEntity
import com.yudhae.kokasappstarter.core.data.source.remote.response.KokasResponse
import com.yudhae.kokasappstarter.core.domain.model.Kokas

object DataMapper {
    fun mapResponsesToEntities(input: List<KokasResponse>): List<KokasEntity> {
        val tourismList = ArrayList<KokasEntity>()
        input.map {
            val kokas = KokasEntity(
                id = it.id,
                title = it.title,
                description = it.description,
                ingredients = it.ingredients,
                image = it.image,
                isFavorite = false
            )
            tourismList.add(kokas)
        }
        return tourismList
    }

    fun mapEntitiesToDomain(input: List<KokasEntity>): List<Kokas> =
        input.map {
            Kokas(
                id = it.id,
                title = it.title,
                description = it.description,
                ingradients =it.ingredients,
                image = it.image,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Kokas) = KokasEntity(
        id = input.id,
        title = input.title,
        description = input.description,
        ingredients = input.ingradients,
        image = input.image,
        isFavorite = input.isFavorite
    )

}