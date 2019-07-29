package com.example.catapp.utils.helpers

import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.LinearLayout

class ExpandCollapseViewHelper {
    companion object {
        const val ANIMATION_DURATION = 250L

        fun expandInLinearLayout(view: View) {
            view.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            val targetHeight = view.measuredHeight
            view.layoutParams.height = 1
            view.visibility = View.VISIBLE

            val animation = object : Animation() {
                override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                    view.layoutParams.height = if (interpolatedTime == 1f)
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    else
                        (targetHeight * interpolatedTime).toInt()
                    view.requestLayout()
                }
            }
            startAnimate(view,animation)

        }

        fun collapseInLinearLayout(view: View) {
            val initialHeight = view.measuredHeight

            val animation = object : Animation() {
                override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                    if (interpolatedTime == 1f) {
                        view.visibility = View.GONE
                    } else {
                        view.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                        view.requestLayout()
                    }
                }
            }
            startAnimate(view,animation)

        }

        private fun startAnimate(view: View, anim: Animation) {
            view.startAnimation(anim)
        }
    }


}