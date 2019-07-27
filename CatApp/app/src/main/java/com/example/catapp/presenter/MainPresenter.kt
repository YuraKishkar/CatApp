package com.example.catapp.presenter

import android.annotation.SuppressLint
import com.example.catapp.presenter.base.BasePresenter
import com.example.catapp.view.base.interfaces.IBaseView
import com.example.catapp.view.fragment.listFragment.CatsItemData
import com.example.catapp.view.interfaces.IMainFragmet
import javax.inject.Inject

class MainPresenter @Inject constructor() : BasePresenter() {

    private lateinit var mParentView: IMainFragmet

    override fun onCreate(iBaseView: IBaseView) {
        this.mParentView = iBaseView as IMainFragmet
        loadCatsData()
    }

    override fun onStop() {
    }


    @SuppressLint("CheckResult")
    fun loadCatsData() {
        addCompositeDisposable(
            mIModel.getCats()
                .map { list -> CatsItemData.MAP_TO_CATS_ITEM(list) }
                .observeOn(mUIThread)
                .subscribe({ result -> mParentView.showResults(result) },
                    { mParentView.showError(it.message.toString()) })
        )


    }

}


