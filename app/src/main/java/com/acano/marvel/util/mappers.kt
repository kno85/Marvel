package com.acano.marvel.util

import com.acano.marvel.network.model.Hero

fun toDomainCharacters(items: List<Hero>?): List<com.acano.marvel.domain.Hero>? {
        if (items != null) {
            return(items.map {
                com.acano.marvel.domain.Hero(
                    id= it.id,
                    name = it.name,
                    description = it.description,
                    image = it.thumbnail?.path?.plus(".").plus(it.thumbnail?.extension)
                )
            })
        }
        else
            return emptyList()
    }
fun toDomainCharacter(item: Hero?): com.acano.marvel.domain.Hero? {
    if (item != null) return(com.acano.marvel.domain.Hero(
            id= item.id,
            name = item.name,
            description = item.description,
            image = item.thumbnail?.path?.plus(".").plus(item.thumbnail?.extension)
        ))
    else
        return com.acano.marvel.domain.Hero()
}
