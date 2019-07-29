package com.example.catapp.utils.helpers

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.catapp.R
import com.google.android.material.snackbar.Snackbar

object SnackBarHelper {
    fun showSnackBar(
        view: View,
        context: Context,
        message: String,
        duration: Int
    ): Snackbar {

        val snackBar = Snackbar.make(view, message, duration)
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
        val textView = snackBarView.findViewById<TextView>(R.id.snackbar_text)
        textView.setTextColor(ContextCompat.getColor(context, R.color.colorSnack))
        textView.maxLines = 10
        snackBar.show()
        return snackBar
    }
}