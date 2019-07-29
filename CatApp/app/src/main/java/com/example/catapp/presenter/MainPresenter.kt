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
     fun loadCatsData() {
        mParentView.showProgressDialog()

        addCompositeDisposable(
            mIModel.getCats()
                .map { list -> CatsItemData.MAP_TO_CATS_ITEM(list) }
                .observeOn(mUIThread)
                .subscribe({ result ->
                    mParentView.dismissProgressDialog()
                    mParentView.showResults(result)
                },
                    { mParentView.showMessageSnack(it.message.toString()) })
        )


    }

    fun addToFavouriteCat(cat: CatsItemData) {
        mParentView.showProgressDialog()
        addCompositeDisposable(
            Observable.just(cat)
                .map { CatsItemData.MAP_TO_FAVOURITE(it) }
                .flatMapCompletable { mIModel.addFavouriteCat(it) }
                .observeOn(mUIThread)
                .subscribe(
                    { mParentView.dismissProgressDialog() },
                    { mParentView.showMessageSnack(it.message.toString()) })
        )
    }
}




