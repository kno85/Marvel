package com.acano.marvel.network

import com.acano.marvel.BuildConfig
import com.acano.marvel.network.CharactersBuilder.apiRest
import com.acano.marvel.network.model.Data
import com.acano.marvel.network.model.Hero
import com.acano.marvel.util.getHash
import retrofit2.Response
import kotlin.random.Random

class RetrofitDataSource : RemoteDateSource {


    private val LIMIT: Int=100
    val ts:String= Random.nextInt().toString()
    val apiKey = BuildConfig.API_CLIENT_ID
    val hash= getHash(ts)
    override suspend fun getHeroList(): Response<Data> {
        return  apiRest.getCharacters(ts,apiKey, hash, LIMIT).execute()

    }


    override suspend fun getCharacter(id:Int): Response<Data>?  {
        return apiRest.getCharacter(id,ts,apiKey, hash).execute()
    }
}
interface RemoteDateSource {
    suspend fun getHeroList(): Response<Data>
    suspend fun getCharacter(id:Int): Response<Data>?
}


