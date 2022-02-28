package com.acano.marvel.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acano.marvel.domain.Hero
import com.acano.marvel.repository.DataRepository
import com.acano.marvel.usecases.UseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


class DetailViewModel(val repo: DataRepository) : ViewModel() {

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
        UseCases(repo).checkItem(id).let {
            if (it != null) {
                    emit(it)
                }
    }}.flowOn(Dispatchers.IO)


}