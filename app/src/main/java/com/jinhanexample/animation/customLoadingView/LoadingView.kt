package com.jinhanexample.animation.customLoadingView

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.jinhanexample.R

class LoadingView : View {

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, @Nullable attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    private lateinit var paint: Paint
    private var circleRadius = 100F
    private var valueAnimator: ValueAnimator? = null


    fun showLoading() {
        isVisible = true
        valueAnimator = ValueAnimator.ofFloat(10F, circleRadius).apply {
            duration = 1000
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener { animation ->
                circleRadius = animation.animatedValue as Float
                animation.repeatCount = ValueAnimator.INFINITE
                animation.repeatMode = ValueAnimator.REVERSE
                invalidate()
            }
            start()
        }
    }

    fun hideLoading() {
        isVisible = false
        valueAnimator?.end()
    }

    private fun init(context: Context, attributeSet: AttributeSet?) {

        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.LoadingView)
        val loadingColor = typedArray.getColor(
            R.styleable.LoadingView_lv_color,
            ContextCompat.getColor(context, R.color.teal_700)
        )

        paint = Paint().apply {
//            color = ContextCompat.getColor(context, R.color.teal_700)
            color = loadingColor
            style = Paint.Style.STROKE
            strokeWidth = 20F
        }

        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(width / 2F, height / 2F, circleRadius, paint)
    }
}