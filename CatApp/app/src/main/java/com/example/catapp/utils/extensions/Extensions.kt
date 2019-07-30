package com.example.catapp.utils.extensions

import android.graphics.drawable.Drawable
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.catapp.R
import com.example.catapp.utils.helpers.ExpandCollapseViewHelper
import com.facebook.shimmer.ShimmerFrameLayout

fun ImageView.loadImageFromUrlWithShimmer(itemView: View, url: String, shimmerFrameLayout: ShimmerFrameLayout) {
    shimmerFrameLayout.startShimmer()
    Glide.with(itemView)
        .load(url)
        .apply {
            diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_cloud_download_24dp)
            centerCrop()
        }
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                shimmerFrameLayout.stopShimmer()
                shimmerFrameLayout.clearAnimation()
                return false
            }
        })
        .into(this)

}

fun ImageView.loadImageFromUrl(itemView: View, url: String) {
    Glide.with(itemView)
        .load(url)
        .apply {
            diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_cloud_download_24dp)
            centerCrop()
        }
        .into(this)
}

fun LinearLayout.expand(expandable: View, arrow: ImageView) {
    when (expandable.visibility) {
        View.VISIBLE -> {
            val animation =
                RotateAnimation(180F, 0F, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.duration = ExpandCollapseViewHelper.ANIMATION_DURATION
            ExpandCollapseViewHelper.collapseInLinearLayout(expandable)
            arrow.rotation = 90f
            arrow.startAnimation(animation)
        }
        else -> {
            val animation =
                RotateAnimation(180F, 0F, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.duration = ExpandCollapseViewHelper.ANIMATION_DURATION
            ExpandCollapseViewHelper.expandInLinearLayout(expandable)
            arrow.rotation = 270f
            arrow.startAnimation(animation)
        }
    }
}