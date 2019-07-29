package com.example.catapp.view.interfaces

interface IEventActivityListener {
    fun showProgressDialog()
    fun dismissProgressDialog()
    fun setTitle(title: String)
    fun showToolbar()
    fun hideToolbar()
}