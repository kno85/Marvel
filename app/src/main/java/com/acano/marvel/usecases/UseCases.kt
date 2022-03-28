package com.acano.marvel.usecases

import com.acano.marvel.domain.Hero
import com.acano.marvel.network.model.Data
import com.acano.marvel.repository.DataRepository
import com.acano.marvel.ui.detail.UiDetailResult
import com.acano.marvel.ui.main.UiResult
import retrofit2.Response

class UseCases(val dataRepository: DataRepository) :UseCasesInterface{
    override suspend fun invokeList():UiResult {
        return dataRepository.getList()
    }
    override suspend fun checkItem(id:Int):UiDetailResult {
        return dataRepository.getHero(id)
    }
}
