package com.acano.marvel.usecases

import com.acano.marvel.ui.detail.UiDetailResult
import com.acano.marvel.ui.main.UiResult

interface UseCasesInterface:BaseUseCase  {
    suspend fun invokeList(): UiResult
    suspend  fun checkItem(id:Int): UiDetailResult
}
