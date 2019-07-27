package com.example.catapp.di.module

import com.example.catapp.model.network.ApiInterface
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetWorkModule {

    @Provides
    @Singleton
    fun provideApiCurrency(): ApiInterface =
        with(Retrofit.Builder()) {
            baseUrl("https://api.thecatapi.com/v1/")
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            build()
        }.create(ApiInterface::class.java)
}