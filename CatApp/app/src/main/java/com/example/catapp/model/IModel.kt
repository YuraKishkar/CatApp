package com.example.catapp.model

import com.example.catapp.model.dto.CatsItemsDTO
import com.example.catapp.view.fragment.favouriteFragment.FavouriteEntity
import io.reactivex.Completable
import io.reactivex.Observable


interface IModel {
    fun getCats(): Observable<List<CatsItemsDTO>>
    fun addFavouriteCat(itemData: FavouriteEntity): Completable
    fun getCatsFavourite(): Observable<List<FavouriteEntity>>
    fun deleteaFavouriteCat(item: FavouriteEntity) : Completable

}