package com.acano.marvel.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acano.marvel.domain.Hero
import com.acano.marvel.network.RetrofitDataSource
import com.acano.marvel.usecases.UseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {

    private val _heroList = MutableLiveData<List<Hero>>()
    val heroList: LiveData<List<Hero>> = _heroList



    init {
        submitHeroList()
    }

    private fun submitHeroList() {
        viewModelScope.launch {
            fetchHeroList()
                .onStart {
                  //  _showProgressBar.postValue(true)
                }.catch { err ->
                   // _showProgressBar.postValue(false)
                }
                .collect { list ->
                   // _showProgressBar.postValue(false)
                    _heroList.value = list
                }
        }
    }


    private fun fetchHeroList() = flow<List<Hero>> {
        delay(700)
        UseCases(RetrofitDataSource()).invokeList()?.let { emit(it) }
    }.flowOn(Dispatchers.IO)


}