package com.example.catapp.presenter.base

import com.example.catapp.App
import com.example.catapp.constants.Constants
import com.example.catapp.model.IModel
import com.example.catapp.presenter.base.interfaces.IBasePresenter
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
    protected lateinit var mIModel: IModel

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

}
