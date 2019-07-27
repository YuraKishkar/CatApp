package com.example.catapp.di.module

import com.example.catapp.constants.Constants
import com.example.catapp.model.IModel
import com.example.catapp.model.ModelImpl
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class PresenterModule {


    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @Singleton
    fun provideModel(): IModel = ModelImpl()

    @Provides
    @Singleton
    @Named(Constants.UI_THREAD)
    fun provideUIThread(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    @Named(Constants.IO_THREAD)
    fun provideIOThread(): Scheduler = Schedulers.io()

}