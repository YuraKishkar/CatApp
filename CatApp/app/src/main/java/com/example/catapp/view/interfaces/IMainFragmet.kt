package com.example.catapp.view.interfaces

import com.example.catapp.view.base.interfaces.IBaseView
import com.example.catapp.view.fragment.listFragment.CatsItemData

interface IMainFragmet: IBaseView {

    fun showResults(listCats: List<CatsItemData>)
}