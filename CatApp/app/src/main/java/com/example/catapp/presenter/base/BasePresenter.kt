package com.example.catapp.presenter.base

import android.content.Context
import android.widget.Toast
import com.example.catapp.App
import com.example.catapp.constants.Constants
import com.example.catapp.model.IModel
import com.example.catapp.presenter.base.interfaces.IBasePresenter
import com.example.catapp.utils.helpers.ImageSaveHelper
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Named


@Suppress("LeakingThis")
abstract class BasePresenter : IBasePresenter {

    @Inject
    lateinit var mCompositeDisposable: CompositeDisposable

    @Inject
    lateinit var mImageSaveHelper: ImageSaveHelper

    @Inject
    protected lateinit var mIModel: IModel

    @Inject
    protected lateinit var mContext: Context

    @field:[Inject
    Named(Constants.UI_THREAD)]
    protected lateinit var mUIThread: Scheduler
    @field:[Inject
    Named(Constants.IO_THREAD)]
    protected lateinit var mIOThread: Scheduler


    init {
        App.instance.getComponent().inject(this)
    }


    protected fun addCompositeDisposable(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }

    override fun onStop() {
        mCompositeDisposable.clear()
    }


    fun downloadImage(id: String, url: String) {
        addCompositeDisposable(
            Observable.just(id)
                .flatMap { mImageSaveHelper.downloadImage(it, url) }
                .subscribeOn(mIOThread)
                .observeOn(mUIThread)
                .subscribe(
                    { Toast.makeText(mContext, it, Toast.LENGTH_LONG).show() },
                    { getBaseView().showMessageSnack(it.message.toString()) })
        )
    }

    override fun hasPermission(): Boolean = mImageSaveHelper.hasPermission()

}
