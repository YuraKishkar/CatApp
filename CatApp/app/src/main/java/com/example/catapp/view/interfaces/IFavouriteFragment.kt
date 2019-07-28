package com.example.catapp.view.interfaces

import com.example.catapp.view.base.interfaces.IBaseView
import com.example.catapp.view.fragment.favouriteFragment.FavouriteEntity

interface IFavouriteFragment : IBaseView {

    fun showResults(list: List<FavouriteEntity>)
    fun updateUI(position: Int)
}