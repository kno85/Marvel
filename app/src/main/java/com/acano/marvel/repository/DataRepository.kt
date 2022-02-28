package com.acano.marvel.repository

import com.acano.marvel.network.model.Data
import com.acano.marvel.network.model.ErrorResponse
import com.acano.marvel.ui.detail.UiDetailResult
import com.acano.marvel.ui.main.UiResult
import com.acano.marvel.util.toDomainCharacter
import com.acano.marvel.util.toDomainCharacters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

class DataRepository(val remoteDataSource: RemoteDataSource) {

    suspend fun getList():UiResult {
        var uiResult=UiResult(null,"")
        var errorMessage:String=""
        val gson = Gson()
        val type = object : TypeToken<ErrorResponse>() {}.type

        val result=remoteDataSource.getHeroList()
        if(result.isSuccessful){
            uiResult= UiResult(toDomainCharacters(result.body()?.results?.items),"")
        }else{
            var errorResponse: ErrorResponse? = gson.fromJson(result.errorBody()!!.charStream(), type)
            errorMessage=errorResponse?.code.toString()+ errorResponse?.message
            uiResult= UiResult(null,errorMessage)
        }
        return uiResult
    }

    suspend fun getHero(id: Int) :UiDetailResult {
        var uiDetailResult=UiDetailResult(null,"")
        var errorMessage:String=""
        val gson = Gson()
        val type = object : TypeToken<ErrorResponse>() {}.type

        val result=remoteDataSource.getCharacter(id)
        if (result != null) {
            if(result.isSuccessful){
                uiDetailResult= UiDetailResult(toDomainCharacter(result.body()?.results?.items?.get(0)),"")
            }else{
                var errorResponse: ErrorResponse? = gson.fromJson(result.errorBody()!!.charStream(), type)
                errorMessage=errorResponse?.code.toString()+ errorResponse?.message
                uiDetailResult= UiDetailResult(null,errorMessage)
            }
        }
        return uiDetailResult    }

}

interface RemoteDataSource {
     suspend fun getHeroList(): Response<Data>

     suspend fun getCharacter(id:Int): Response<Data>?
}
