package com.acano.marvel.ui.main

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


class MainViewModel() : ViewModel(), KoinComponent {
    private val useCases : UseCases by inject()

    private val _heroList = MutableLiveData<List<Hero>>()
    val heroList: LiveData<List<Hero>> = _heroList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _showExitDialog= MutableLiveData<Boolean>()
    val showExitDialog: LiveData<Boolean> = _showExitDialog



    init {
        _showExitDialog.value = false
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
                        _heroList.value = list.heroList!!
                    }else{
                        _errorMessage.value= list.errorMessage!!
                    }
                }
        }
    }


    private suspend fun fetchHeroList() = flow<UiResult> {
        delay(700)
        useCases.invokeList().let {
           emit(it)
        }
    }.flowOn(Dispatchers.IO)

    fun userPressBackButton() {
        _showExitDialog.value = !_showExitDialog.value!!
    }


}
