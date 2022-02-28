package com.acano.marvel.di

import com.acano.marvel.repository.DataRepository
import com.acano.marvel.repository.RemoteDataSource
import org.koin.dsl.module

val RepositoryModule = module {
        fun provideUserRepository(dataSource: RemoteDataSource): DataRepository {
            return DataRepository(dataSource)
    }
        single { provideUserRepository(get()) }
    }
