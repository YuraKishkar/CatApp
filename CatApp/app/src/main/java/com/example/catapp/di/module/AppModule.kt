package com.example.catapp.di.module

import android.app.Application
import android.content.Context
import com.example.catapp.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(private val app: App) {


    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext


}