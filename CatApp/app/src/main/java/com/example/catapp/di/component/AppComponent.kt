package com.example.catapp.di.component

import com.example.catapp.di.module.AppModule
import com.example.catapp.di.module.NetWorkModule
import com.example.catapp.di.module.PresenterModule
import com.example.catapp.model.ModelImpl
import com.example.catapp.presenter.MainPresenter
import com.example.catapp.presenter.base.BasePresenter
import com.example.catapp.view.fragment.listFragment.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, PresenterModule::class, NetWorkModule::class])
interface AppComponent {
    //Presenters
    fun inject(mainPresenter: MainPresenter)
    fun inject(basePresenter: BasePresenter)

    fun inject(mainFragment: MainFragment)

    //Model
    fun inject(modelImpl: ModelImpl)
}