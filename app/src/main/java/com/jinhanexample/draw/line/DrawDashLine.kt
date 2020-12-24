package com.jinhanexample.draw.line

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.util.AttributeSet
import android.util.LogPrinter
import android.util.TypedValue
import android.view.View
import com.jinhanexample.Common

class DrawDashLine : View {

    private var viewWidth : Int =0
    private var viewHeight : Int =0

    constructor(context: Context?, viewWidth: Int, viewHeight: Int) : super(context) {
        this.viewWidth = viewWidth
        this.viewHeight = viewHeight
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val dashWidth = getDP(context, 3)
        val dashPaint = Paint()
        dashPaint.isAntiAlias = true
        dashPaint.color = Color.parseColor("#e61616")
        dashPaint.style = Paint.Style.STROKE
        dashPaint.strokeWidth = dashWidth
        dashPaint.pathEffect = DashPathEffect(floatArrayOf(dashWidth, dashWidth * 2), 0f)
        //floatArrayOf의 첫번째는 점선의 width, 두번째는 점선의 간격
        //phase는 점선이 시작할 때 잘려나가는 이미지 값의 허용범위

        val startX = 0f
        val stopX = viewWidth


        val y = 0f
        canvas?.drawLine(startX, y, stopX.toFloat(), y, dashPaint)
    }


    fun getDP(context: Context, value: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value.toFloat(),
            context.resources.displayMetrics
        )
    }
}