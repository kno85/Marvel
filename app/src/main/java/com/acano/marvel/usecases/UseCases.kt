package com.acano.marvel.usecases

import com.acano.marvel.domain.Hero
import com.acano.marvel.network.RetrofitDataSource
import com.acano.marvel.network.model.Data
import retrofit2.Response

class UseCases(val charactersRepository: RetrofitDataSource) {
    suspend fun invokeList(): Response<Data> {
        return charactersRepository.getHeroList()
    }
    suspend fun checkItem(id:Int): Response<com.acano.marvel.network.model.Data>? {
        return charactersRepository.getCharacter(id)
    }
}
