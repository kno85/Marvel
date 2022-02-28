package com.acano.marvel.network

import com.acano.marvel.repository.RemoteDataSource
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val NetworkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { remoteDatasourceProvider() }
    single { provideApiService(get(), ApiService::class.java) }
}
fun remoteDatasourceProvider() : RemoteDataSource = RetrofitDataSource()

fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()

fun provideApiService(retrofit: Retrofit, apiService: Class<ApiService>) =
    createService(retrofit, apiService)

fun <T> createService(retrofit: Retrofit, serviceClass: Class<T>): T = retrofit.create(serviceClass)