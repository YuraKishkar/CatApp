package com.example.catapp.view.base.fragment

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.catapp.presenter.base.interfaces.IBasePresenter
import com.example.catapp.utils.helpers.SnackBarHelper
import com.example.catapp.view.base.interfaces.IBaseView
import com.example.catapp.view.fragment.favouriteFragment.FavouriteFragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment(), IBaseView {

    companion object {
        const val REQUEST_WRITE_STORAGE = 228
    }

    protected abstract fun getLayoutResId(): Int
    protected abstract fun getPresenter(): IBasePresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutResId(), container, false)
        onCreateView(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
    }

    override fun onStop() {
        super.onStop()
        getPresenter().onStop()
    }

    protected open fun onCreateView(view: View) {}

    override fun showError(messsage: String) {
        view?.let {
            context?.let { context ->
                SnackBarHelper.showSnackBar(
                    it,
                    context,
                    messsage,
                    Snackbar.LENGTH_LONG
                )
            }
        }
//        view?.let { Snackbar.make(it, messsage, Snackbar.LENGTH_LONG).show() }
    }

    private fun checkPermission() {
        if (!getPresenter().hasPermission()) {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_WRITE_STORAGE
                )
            }
        }

    }

}