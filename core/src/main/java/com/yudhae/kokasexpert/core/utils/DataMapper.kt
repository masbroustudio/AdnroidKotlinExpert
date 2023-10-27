package com.yudhae.kokasexpert.core.utils

import com.yudhae.kokasexpert.core.data.source.local.entity.KokasEntity
import com.yudhae.kokasexpert.core.data.source.remote.response.KokasResponse
import com.yudhae.kokasexpert.core.domain.model.Kokas

object DataMapper {
    fun mapResponsesToEntities(input: List<KokasResponse>): List<KokasEntity> {
        val kokasList = ArrayList<KokasEntity>()
        input.map {
            val kokas = KokasEntity(
                kokasId = it.id,
                description = it.description,
                name = it.name,
                address = it.address,
                latitude = it.latitude,
                longitude = it.longitude,
                like = it.like,
                image = it.image,
                isFavorite = false
            )
            kokasList.add(kokas)
        }
        return kokasList
    }

    fun mapEntitiesToDomain(input: List<KokasEntity>): List<Kokas> =
        input.map {
            Kokas(
                kokasId = it.kokasId,
                description = it.description,
                name = it.name,
                address = it.address,
                latitude = it.latitude,
                longitude = it.longitude,
                like = it.like,
                image = it.image,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Kokas) = KokasEntity(
        kokasId = input.kokasId,
        description = input.description,
        name = input.name,
        address = input.address,
        latitude = input.latitude,
        longitude = input.longitude,
        like = input.like,
        image = input.image,
        isFavorite = input.isFavorite
    )
}