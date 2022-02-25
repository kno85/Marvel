package com.acano.marvel.usecases

import com.acano.marvel.domain.Hero
import com.acano.marvel.network.RetrofitDataSource

class UseCases(val charactersRepository: RetrofitDataSource) {
    suspend fun invokeList(): List<Hero>? {
        return charactersRepository.getHeroList()
    }
    suspend fun checkItem(id:Int): Character? {
        return charactersRepository.getCharacter(id)
    }
}
