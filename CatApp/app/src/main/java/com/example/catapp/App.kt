package com.example.catapp

import android.app.Application
import com.example.catapp.di.component.AppComponent
import com.example.catapp.di.component.DaggerAppComponent
import com.example.catapp.di.module.AppModule

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        buildComponent()
    }

    fun getComponent() = appComponent

    private fun buildComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(instance))
            .build()
    }
}