package com.jinhanexample.animation.progress.circle

import android.view.animation.Animation
import android.view.animation.Transformation

class CircleProgressAnimation(
    private var circleProgress: CircleProgress,
    private val startAngle: Int,
    private val toAngle: Int,
    animDuration: Long
) : Animation() {


    init {
        duration = animDuration
        circleProgress.visible = true
    }


    //interpolatedTime은 0부터 1까지 증가하는 값
    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime, t)

        var angle = (startAngle.toFloat() + ((toAngle - startAngle) *interpolatedTime))

        circleProgress.endAngle = angle
        circleProgress.requestLayout()

    }
}