package com.jinhanexample.animation.progress.circle

import android.util.Log
import android.view.animation.Animation
import android.view.animation.Transformation

class CircleAnimation(
    private var circle: Circle,
    private var startAngle: Float,
    private var endAngle: Float,
    private var color: Int,
    private var alpha: Int,
    private var bLine: Boolean,
    private var showEndCircle: Boolean
) : Animation() {

    private val DURATION: Long = 1000

    init {
        circle.startAngle = startAngle
        circle.color = color
        circle.alpha = alpha
        circle.bLine = bLine
        circle.bEndCircle = showEndCircle
        duration = DURATION

    }

    //interpolatedTime은 0부터 1까지 증가하는 값
    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime, t)
        circle.visible = true

        //endPoint = start 포지션 기준으로 최종 도달할 각도
        var angle: Float = (endAngle * interpolatedTime)
        circle.moveAngle = angle
        circle.requestLayout()

        //+면 시계방향
        //-면 반시계방향
    }
}