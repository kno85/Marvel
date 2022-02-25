package com.acano.marvel.network

import com.acano.marvel.BuildConfig
import com.acano.marvel.domain.Hero
import com.acano.marvel.network.CharactersBuilder.apiRest
import com.acano.marvel.util.getHash
import com.acano.marvel.util.toDomainCharacter
import kotlin.random.Random

class RetrofitDataSource : RemoteDateSource {


    private val LIMIT: Int=100
    val ts:String= Random.nextInt().toString()
    val apiKey = BuildConfig.API_CLIENT_ID
    val hash= getHash(ts)
    override suspend fun getHeroList(): List<Hero>?  {

        val query=apiRest.getCharacters(ts,apiKey, hash, LIMIT).execute()
        return toDomainCharacter(query.body()?.results?.items)
    }
    override suspend fun getCharacter(id:Int): Character?  {
       // val query=apiRest.getCharacter(id,ts,apiKey, hash).execute()
      //  return toDomainCharacterResult(query.body()?.results?.items?.get(0))
   return null
    }
}
interface RemoteDateSource {
    suspend fun getHeroList(): List<Hero>?
    suspend fun getCharacter(id:Int): Character?
}


