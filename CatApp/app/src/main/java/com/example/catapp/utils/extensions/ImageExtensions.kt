package com.example.catapp.utils.extensions

import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.catapp.utils.helpers.ExpandCollapseViewHelper

fun ImageView.loadImageFromUrl(itemView: View, url: String) {
    Glide.with(itemView)
        .load(url)
        .apply {
            diskCacheStrategy(DiskCacheStrategy.ALL)
            centerCrop()
            into(this@loadImageFromUrl)
        }
}

fun LinearLayout.expand(expandable: View, arrow: ImageView) {
    when (expandable.visibility) {
        View.VISIBLE -> {
            val animation = RotateAnimation(180F, 0F, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.duration = ExpandCollapseViewHelper.ANIMATION_DURATION
            ExpandCollapseViewHelper.collapseInLinearLayout(expandable)
            arrow.rotation = 180f
            arrow.startAnimation(animation)
        }
        else -> {
            val animation = RotateAnimation(180F, 0F, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.duration = ExpandCollapseViewHelper.ANIMATION_DURATION
            ExpandCollapseViewHelper.expandInLinearLayout(expandable)
            arrow.rotation = 90f
            arrow.startAnimation(animation)
        }
    }
}