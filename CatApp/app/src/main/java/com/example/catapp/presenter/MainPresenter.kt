package com.example.catapp.presenter

import android.annotation.SuppressLint
import com.example.catapp.presenter.base.BasePresenter
import com.example.catapp.view.base.interfaces.IBaseView
import com.example.catapp.view.fragment.listFragment.CatsItemData
import com.example.catapp.view.interfaces.IMainFragmet
import io.reactivex.Observable
import javax.inject.Inject

class MainPresenter @Inject constructor() : BasePresenter() {

    private lateinit var mParentView: IMainFragmet

    override fun onCreate(iBaseView: IBaseView) {
        this.mParentView = iBaseView as IMainFragmet
        loadCatsData()
    }

    override fun onStop() {
    }

    override fun getBaseView(): IBaseView = mParentView

    @SuppressLint("CheckResult")
    private fun loadCatsData() {
        addCompositeDisposable(
            mIModel.getCats()
                .map { list -> CatsItemData.MAP_TO_CATS_ITEM(list) }
                .observeOn(mUIThread)
                .subscribe({ result -> mParentView.showResults(result) },
                    { mParentView.showError(it.message.toString()) })
        )


    }

    fun addToFavouriteCat(cat: CatsItemData) {
        addCompositeDisposable(
            Observable.just(cat)
                .map { CatsItemData.MAP_TO_FAVOURITE(it) }
                .flatMapCompletable { mIModel.addFavouriteCat(it) }
                .observeOn(mUIThread)
                .subscribe({}, { mParentView.showError(it.message.toString()) })
        )
    }
}




