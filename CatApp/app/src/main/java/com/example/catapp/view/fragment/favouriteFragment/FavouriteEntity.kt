package com.example.catapp.view.fragment.favouriteFragment

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_table")
data class FavouriteEntity(
    @PrimaryKey
    var id: String,
    var url: String
)