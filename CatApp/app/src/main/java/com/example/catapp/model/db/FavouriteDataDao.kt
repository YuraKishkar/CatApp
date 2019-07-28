package com.example.catapp.model.db

import androidx.room.*
import com.example.catapp.view.fragment.favouriteFragment.FavouriteEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface FavouriteDataDao {

    @Query("SELECT * FROM favourite_table")
    fun getAll(): Observable<List<FavouriteEntity>>

    @Query("SELECT * FROM favourite_table WHERE id = :id")
    fun getById(id: String): Single<FavouriteEntity>

    @Insert
    fun insert(favouriteEntity: FavouriteEntity): Completable

    @Update
    fun update(favouriteEntity: FavouriteEntity)

    @Delete
    fun delete(favouriteEntity: FavouriteEntity): Completable
}