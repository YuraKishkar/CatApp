package com.example.catapp.view.base.activity

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.catapp.R
import com.example.catapp.utils.helpers.DialogHepler
import com.example.catapp.view.interfaces.IEventActivityListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*

abstract class BaseActivity : AppCompatActivity(), IEventActivityListener {
    protected abstract fun getLayoutResId(): Int

    private lateinit var mTitleToolBar: TextView
    private lateinit var mToolBar: Toolbar

    private var mAlertDialog: AlertDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
    }


    override fun showProgressDialog() {
        dismissProgressDialog()
        mAlertDialog = DialogHepler.showProgressDialog(this, R.string.title_progress_dialog, 0)
    }


    override fun dismissProgressDialog() {
        mAlertDialog?.dismiss()
    }

    override fun setTitle(title: String) {
        tv_title.text = title
    }

    override fun showToolbar() {
        id_toolbar.visibility = View.VISIBLE
    }

    override fun hideToolbar() {
        id_toolbar.visibility = View.GONE
    }
}