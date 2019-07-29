package com.example.catapp.presenter.base.interfaces

import com.example.catapp.view.base.interfaces.IBaseView

interface IBasePresenter {
    fun onCreate(iBaseView: IBaseView)
    fun onStop()
    fun getBaseView(): IBaseView
    fun hasPermission(): Boolean
}