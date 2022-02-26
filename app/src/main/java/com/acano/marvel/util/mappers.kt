package com.acano.marvel.util

import com.acano.marvel.network.model.Hero

fun toDomainCharacter(items: List<Hero>?): List<com.acano.marvel.domain.Hero>? {
        if (items != null) {
            return(items.map {
                com.acano.marvel.domain.Hero(
                    name = it.name,
                    description = it.description,
                    image = it.thumbnail?.path?.plus(".").plus(it.thumbnail?.extension)
                )
            })
        }
        else
            return emptyList()
    }
