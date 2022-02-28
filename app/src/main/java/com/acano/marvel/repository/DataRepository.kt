package com.acano.marvel.repository

import com.acano.marvel.network.model.Data
import retrofit2.Response

class DataRepository(val remoteDataSource: RemoteDataSource) {

    suspend fun getList():Response<Data> = remoteDataSource.getHeroList()

    suspend fun getHero(id: Int) : Response<Data>? = remoteDataSource.getCharacter(id)

}

interface RemoteDataSource {
     suspend fun getHeroList(): Response<Data>

     suspend fun getCharacter(id:Int): Response<Data>?
}
