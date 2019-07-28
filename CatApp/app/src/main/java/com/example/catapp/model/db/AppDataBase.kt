package com.example.catapp.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catapp.view.fragment.favouriteFragment.FavouriteEntity

@Database(entities = [FavouriteEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun favouriteDataDao(): FavouriteDataDao
}