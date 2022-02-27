package com.acano.marvel.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acano.marvel.domain.Hero
import com.acano.marvel.network.RetrofitDataSource
import com.acano.marvel.network.model.ErrorResponse
import com.acano.marvel.usecases.UseCases
import com.acano.marvel.util.toDomainCharacters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {

    private val _heroList = MutableLiveData<List<Hero>>()
    val heroList: LiveData<List<Hero>> = _heroList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage



    init {
        submitHeroList()
    }

    private fun submitHeroList() {
        viewModelScope.launch {
            fetchHeroList()
                .catch { err ->
                    _errorMessage.value= err.message
                }
                .collect { list ->
                    if(list.heroList!=null){
                        _heroList.value = list.heroList
                    }else{
                        _errorMessage.value= list.errorMessage
                    }
                }
        }
    }


    private suspend fun fetchHeroList() = flow<UiResult> {
        delay(700)
        UseCases(RetrofitDataSource()).invokeList().let {
            val result= it
            if(it.errorBody()!=null){
                val gson = Gson()
                val type = object : TypeToken<ErrorResponse>() {}.type
                var errorResponse: ErrorResponse? = gson.fromJson(it.errorBody()!!.charStream(), type)
                emit(UiResult(null,"ERROR_CODE: "+errorResponse?.code.toString()+" "+errorResponse?.message))
            }else{
                result.let { it1 -> toDomainCharacters(it1.body()?.results?.items)?.let { it2 -> emit(UiResult(it2,"")) } }
            }
        }
    }.flowOn(Dispatchers.IO)


}