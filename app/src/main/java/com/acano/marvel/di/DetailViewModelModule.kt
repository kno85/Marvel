package com.acano.marvel.di

import com.acano.marvel.ui.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module


    val DetailViewModelModule = module {
        viewModel { DetailViewModel() }
    }
