package com.example.catapp.view.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.catapp.presenter.base.interfaces.IBasePresenter
import com.example.catapp.view.base.interfaces.IBaseView

abstract class BaseFragment : Fragment(), IBaseView {

    protected abstract fun getLayoutResId(): Int
    protected abstract fun getPresenter(): IBasePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutResId(), container, false)
        onCreateView(view)
        return view
    }

    override fun onStop() {
        super.onStop()
        getPresenter().onStop()
    }

     protected open fun onCreateView(view: View){}

    override fun showError(messsage: String) {
    }
}