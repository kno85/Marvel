package com.acano.marvel

import android.app.Application
import com.acano.marvel.di.DetailViewModelModule
import com.acano.marvel.di.MainViewModelModule
import com.acano.marvel.di.RepositoryModule
import com.acano.marvel.network.NetworkModule
import com.acano.marvel.usecases.UseCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {


    override fun onCreate() {

        val appModules = listOf(NetworkModule,UseCasesModule, RepositoryModule, MainViewModelModule, DetailViewModelModule)
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(appModules)
        }
    }
}