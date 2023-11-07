package com.kokassubexpert01.core.utils

import com.kokassubexpert01.core.data.source.local.entity.KokasEntity
import com.kokassubexpert01.core.data.source.remote.response.KokasResponse
import com.kokassubexpert01.core.domain.model.Kokas

object DataMapper {
    fun mapResponsesToEntities(input: List<KokasResponse>): List<KokasEntity> {
        val tourismList = ArrayList<KokasEntity>()
        input.map {
            val tourism = KokasEntity(
                kokasId = it.id,
                description = it.description,
                title = it.title,
                ingredients = it.ingredients,
                image = it.image,
                isFavorite = false
            )
            tourismList.add(tourism)
        }
        return tourismList
    }

    fun mapEntitiesToDomain(input: List<KokasEntity>) : List<Kokas> = input.map{
        Kokas(
            kokasId = it.kokasId,
            title = it.title,
            description = it.description,
            ingredients = it.ingredients,
            image = it.image,
            isFavorite = it.isFavorite
        )
    }

    fun mapDomainToEntity(input: Kokas) = KokasEntity(
        kokasId = input.kokasId,
        title = input.title,
        description = input.description,
        ingredients = input.ingredients,
        image = input.image,
        isFavorite = input.isFavorite
    )
}