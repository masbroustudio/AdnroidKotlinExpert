package com.kokassubexpert01.core.utils

import com.kokassubexpert01.core.data.source.local.entity.TourismEntity
import com.kokassubexpert01.core.data.source.remote.response.TourismResponse
import com.kokassubexpert01.core.domain.model.Tourism

object DataMapper {
    fun mapResponsesToEntities(input: List<TourismResponse>): List<TourismEntity> {
        val tourismList = ArrayList<TourismEntity>()
        input.map {
            val tourism = TourismEntity(
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

    fun mapEntitiesToDomain(input: List<TourismEntity>) : List<Tourism> = input.map{
        Tourism(
            kokasId = it.kokasId,
            title = it.title,
            description = it.description,
            ingredients = it.ingredients,
            image = it.image,
            isFavorite = it.isFavorite
        )
    }

    fun mapDomainToEntity(input: Tourism) = TourismEntity(
        kokasId = input.kokasId,
        title = input.title,
        description = input.description,
        ingredients = input.ingredients,
        image = input.image,
        isFavorite = input.isFavorite
    )
}