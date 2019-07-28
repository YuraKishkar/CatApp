package com.example.catapp.di.module

import android.content.Context
import androidx.room.Room
import com.example.catapp.model.db.AppDataBase
import com.example.catapp.model.db.FavouriteDataDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {


    @Provides
    @Singleton
    fun provideAppDataBase(context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "favourite_table"
        ).build()
    }


    @Provides
    @Singleton
    fun provideCurrencyDao(appDataBase: AppDataBase): FavouriteDataDao {
        return appDataBase.favouriteDataDao()
    }
}