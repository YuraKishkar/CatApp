package com.example.catapp.model.network

import com.example.catapp.model.dto.CatsItemsDTO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiInterface {

    @Headers("x-api-key: 77c885dc-1ce0-4b1d-8bf3-20f74fef199b")
    @GET("images/search")
    fun getCats(@Query("limit") limit: Int): Observable<List<CatsItemsDTO>>

}