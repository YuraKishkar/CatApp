package com.example.catapp.presenter

import com.example.catapp.presenter.base.BasePresenter
import com.example.catapp.view.base.interfaces.IBaseView
import com.example.catapp.view.fragment.favouriteFragment.FavouriteEntity
import com.example.catapp.view.interfaces.IFavouriteFragment
import javax.inject.Inject

class FavouritePresenter @Inject constructor() : BasePresenter() {

    private lateinit var mParentView: IFavouriteFragment

    override fun onCreate(iBaseView: IBaseView) {
        this.mParentView = iBaseView as IFavouriteFragment
        loadFavouriteCats()
    }


    override fun getBaseView(): IBaseView = mParentView


    private fun loadFavouriteCats() {
        mParentView.showProgressDialog()
        addCompositeDisposable(
            mIModel.getCatsFavourite()
                .observeOn(mUIThread)
                .subscribe({
                    mParentView.dismissProgressDialog()
                    mParentView.showResults(it)

                }, { mParentView.showMessageSnack(it.message.toString()) })
        )
    }

    fun deleteFavouriteCat(cat: FavouriteEntity, position: Int) {
        mParentView.showProgressDialog()
        addCompositeDisposable(
            mIModel.deleteaFavouriteCat(cat)
                .observeOn(mUIThread)
                .subscribe({
                    mParentView.dismissProgressDialog()
                    mParentView.updateUI(position)

                }, { mParentView.showMessageSnack(it.message.toString()) })
        )
    }
}