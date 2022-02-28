package com.acano.marvel

import android.app.Application
import android.util.Log.ERROR
import com.acano.marvel.di.DetailViewModelModule
import com.acano.marvel.di.MainViewModelModule
import com.acano.marvel.di.RepositoryModule
import com.acano.marvel.network.NetworkModule
import com.acano.marvel.usecases.UseCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.util.logging.Level

class App: Application() {


    override fun onCreate() {

        val appModules = listOf(NetworkModule,UseCasesModule, RepositoryModule, MainViewModelModule, DetailViewModelModule)
        super.onCreate()
        startKoin { androidLogger(org.koin.core.logger.Level.ERROR)

            androidContext(this@App)
            modules(appModules)
        }
    }
}