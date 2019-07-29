package com.example.catapp.di.component

import com.example.catapp.di.module.AppModule
import com.example.catapp.di.module.NetWorkModule
import com.example.catapp.di.module.PresenterModule
import com.example.catapp.di.module.RoomModule
import com.example.catapp.model.ModelImpl
import com.example.catapp.presenter.FavouritePresenter
import com.example.catapp.presenter.MainPresenter
import com.example.catapp.presenter.base.BasePresenter
import com.example.catapp.utils.helpers.ImageSaveHelper
import com.example.catapp.view.fragment.favouriteFragment.FavouriteFragment
import com.example.catapp.view.fragment.listFragment.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, PresenterModule::class, NetWorkModule::class, RoomModule::class])
interface AppComponent {
    //Presenters
    fun inject(mainPresenter: MainPresenter)
    fun inject(favouritePresenter: FavouritePresenter)
    fun inject(basePresenter: BasePresenter)

    //Fragment
    fun inject(mainFragment: MainFragment)
    fun inject(favouriteFragment: FavouriteFragment)
    //Model
    fun inject(modelImpl: ModelImpl)

    //Helpers
    fun inject(imageSaveHelper: ImageSaveHelper)
}