package com.acano.marvel.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acano.marvel.domain.Hero
import com.acano.marvel.network.RetrofitDataSource
import com.acano.marvel.network.model.ErrorResponse
import com.acano.marvel.usecases.UseCases
import com.acano.marvel.util.toDomainCharacter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


class DetailViewModel : ViewModel() {

    private val _hero = MutableLiveData<Hero>()
    val hero: LiveData<Hero> = _hero

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage


     fun submitHero(id:Int) {
        viewModelScope.launch {
            fetchHero(id)
                .catch { err ->
                    _errorMessage.value= err.message
                }
                .collect { result ->
                    _hero.value = result.hero!!
                }
        }
    }


    private suspend fun fetchHero(id: Int) = flow<UiDetailResult> {
        delay(700)
        UseCases(RetrofitDataSource()).checkItem(id).let {
            val result= it
            if (it != null) {
                if(it.errorBody()!=null){
                    val gson = Gson()
                    val type = object : TypeToken<ErrorResponse>() {}.type
                    var errorResponse: ErrorResponse? = gson.fromJson(it.errorBody()!!.charStream(), type)
                    emit(UiDetailResult(null,"ERROR_CODE: "+errorResponse?.code.toString()+" "+errorResponse?.message))
                }else{
                    emit(UiDetailResult(toDomainCharacter(it.body()?.results?.items?.get(0)),"")
                    ) } }
                }
    }.flowOn(Dispatchers.IO)


}