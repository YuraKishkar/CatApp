package com.example.catapp.model

import com.example.catapp.model.dto.BaseListDTO
import com.example.catapp.model.dto.CatsItemsDTO
import io.reactivex.Observable


interface IModel {
    fun getCats(): Observable<List<CatsItemsDTO>>
}