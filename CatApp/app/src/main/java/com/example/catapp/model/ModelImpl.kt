package com.example.catapp.model

import com.example.catapp.App
import com.example.catapp.constants.Constants
import com.example.catapp.model.dto.BaseListDTO
import com.example.catapp.model.dto.CatsItemsDTO
import com.example.catapp.model.network.ApiInterface
import io.reactivex.Observable

import io.reactivex.Scheduler

import javax.inject.Inject
import javax.inject.Named

class ModelImpl : IModel {


    @Inject
    lateinit var mApiInterface: ApiInterface

    @field:[Inject
    Named(Constants.UI_THREAD)]
    lateinit var mUIThread: Scheduler
    @field:[Inject
    Named(Constants.IO_THREAD)]
    lateinit var mIOThread: Scheduler

    init {
        App.instance.getComponent().inject(this)
    }

    override fun getCats(): Observable<List<CatsItemsDTO>> =
        mApiInterface.getCats(20)
            .subscribeOn(mIOThread)
}