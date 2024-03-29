package com.acano.marvel.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acano.marvel.domain.Hero
import com.acano.marvel.usecases.UseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class DetailViewModel() : ViewModel(), KoinComponent {
    private val detailUseCases : UseCases by inject()

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
                    if(result.hero!=null){
                        _hero.value = result.hero!!
                    }else{
                        _errorMessage.value= result.errorMessage!!
                    }
                }
        }
    }


    private suspend fun fetchHero(id: Int) = flow<UiDetailResult> {
        delay(700)
        detailUseCases.checkItem(id).let {
                    emit(it)
    }}.flowOn(Dispatchers.IO)


}