package com.acano.marvel.network

import com.acano.marvel.network.model.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


const val BASE_URL:String="https://gateway.marvel.com:443"
const val CharactersEnpoint="/v1/public/characters"
const val CharacterEnpoint="/v1/public/characters/{characterId}"

interface ApiService {
    @GET(CharactersEnpoint)
    fun getCharacters(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int?
    ): Call <Data>

//    @GET(CharacterEnpoint)
//    fun getCharacter(
//        @Path("characterId") characterid: Int,
//        @Query("ts") ts: String,
//        @Query("apikey") apikey: String,
//        @Query("hash") hash: String
//    ): Call <Data>
}

