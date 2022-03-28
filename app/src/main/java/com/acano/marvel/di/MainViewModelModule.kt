package com.acano.marvel.di

import com.acano.marvel.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


    val MainViewModelModule = module {
                viewModel { MainViewModel() }
    }
