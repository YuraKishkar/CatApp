package com.example.catapp.utils.helpers

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.catapp.R

object DialogHepler {
    fun showProgressDialog(activity: Activity, titleText: Int, messageText: Int): AlertDialog {
        val builder = AlertDialog.Builder(activity, R.style.AlertDialogStyle)
            .setTitle(titleText)
            .setView(initProgressView(activity, messageText))
            .setCancelable(false)

        val materialDialog = builder.show()
        materialDialog.window?.setLayout(
            400,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        materialDialog.setCanceledOnTouchOutside(false)
        materialDialog.setCancelable(false)
        return materialDialog

    }


    private fun initProgressView(context: Context, messageTextId: Int): View {
        val view = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null, false)
        val messageTextView = view.findViewById(R.id.tv_message) as TextView
        if (messageTextId != 0) {
            messageTextView.text = context.getString(messageTextId)
            messageTextView.visibility = View.VISIBLE
        } else {
            messageTextView.visibility = View.GONE
        }
        return view
    }
}