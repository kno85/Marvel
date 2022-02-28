package com.acano.marvel.usecases

import org.koin.dsl.module

val UseCasesModule = module {
    factory {
        UseCases(get())
    }
}