package com.jinhanexample.customChart.varChart

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import com.jinhanexample.R

class BarChartView : View {

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(
        context: Context?,
        viewWidth: Float,
        viewHeight: Float,
        barScoreArray: ArrayList<Int>
    ) : super(context) {
        this.viewWidth = viewWidth
        this.viewHeight = viewHeight
        this.barScoreArray = barScoreArray

        initPaint(context!!)
    }

    private var viewWidth: Float = 0f
    private var viewHeight: Float = 0f
    lateinit var viewPaint: Paint
    lateinit var viewStrokePaint: Paint
    lateinit var barGrayPaint: Paint
    private var barScoreArray: ArrayList<Int> = ArrayList()
    private var barWidth = getDP(context, 16)
    private var barBottom = getDP(context, 240)


    private fun initPaint(context: Context) {
        viewPaint = Paint()
        viewPaint.isAntiAlias = true
        viewPaint.color = setColor(context, R.color.blue200)
        viewPaint.style = Paint.Style.FILL

        viewStrokePaint = Paint()
        viewStrokePaint.isAntiAlias = true
        viewStrokePaint.color = setColor(context, R.color.Gray200)
        viewStrokePaint.strokeWidth = getDP(context, 30)
        viewStrokePaint.style = Paint.Style.STROKE
        //TODO::stroke round 적용 안됨
        //TODO:: Bar left 값 다시 계산해서 넣을 것

        barGrayPaint = Paint()
        barGrayPaint.isAntiAlias = true
        barGrayPaint.color = setColor(context, R.color.barColor)
        barGrayPaint.style = Paint.Style.FILL

    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        val viewRectF = RectF(0f, 0f, viewWidth, viewHeight)
        canvas?.drawRoundRect(viewRectF, getDP(context, 4), getDP(context, 4), viewPaint)
        canvas?.drawRoundRect(viewRectF, getDP(context, 10), getDP(context, 10), viewStrokePaint)
        setBarScoreHeight(canvas)

    }

    private fun setBarScoreHeight(canvas: Canvas?) {

        //height의 최대값을 50이라고 가정
        var perBarHeight: Float = viewHeight / 50
        var perBarWidth: Float = viewWidth / 10
        val barMarginFirstLeft = getDP(context, 35) + getDP(context, 22)
        Log.d(TAG, "setBarScoreHeight: perBarHeight : $perBarHeight")
        Log.d(TAG, "setBarScoreHeight: perBarWidth : $perBarWidth")
        Log.d(TAG, "setBarScoreHeight: barMarginFirstLeft : $barMarginFirstLeft")
        for (i in 0 until barScoreArray.size) {

            val bottom = barBottom
            val top = bottom - (perBarHeight * barScoreArray[i])
            val left = (i * perBarWidth) + barMarginFirstLeft + getDP(context, 22)
            Log.d(TAG, "setBarScoreHeight: bottom : $bottom, top : $top, left : $left")
            val barGrayRectF = RectF(left, top, barWidth, bottom)
            canvas?.drawRoundRect(barGrayRectF, 4f, 4f, barGrayPaint)
        }
    }

    private fun setColor(context: Context, color: Int): Int {
        return context.resources.getColor(color, null)
    }

    private fun getDP(context: Context, value: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value.toFloat(),
            context.resources.displayMetrics
        )
    }

    companion object {
        private const val TAG = "BarChartView"
    }
}