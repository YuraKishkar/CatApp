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


    private fun loadFavouriteCats() {
        addCompositeDisposable(
            mIModel.getCatsFavourite()
                .observeOn(mUIThread)
                .subscribe({ mParentView.showResults(it) }, { mParentView.showError(it.message.toString()) })
        )
    }

    fun deleteFavouriteCat(cat: FavouriteEntity, position: Int) {
        addCompositeDisposable(
            mIModel.deleteaFavouriteCat(cat)
                .observeOn(mUIThread)
                .subscribe({mParentView.updateUI(position)}, { mParentView.showError(it.message.toString()) })
        )
    }
}