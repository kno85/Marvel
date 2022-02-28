package com.acano.marvel.usecases

import com.acano.marvel.domain.Hero
import com.acano.marvel.network.model.Data
import com.acano.marvel.repository.DataRepository
import retrofit2.Response

class UseCases(val dataRepository: DataRepository) {
    suspend fun invokeList(): Response<Data> {
        return dataRepository.getList()
    }
    suspend fun checkItem(id:Int): Response<com.acano.marvel.network.model.Data>? {
        return dataRepository.getHero(id)
    }
}
