package com.jinhanexample.animation.progress.circle

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.jinhanexample.R
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.properties.Delegates

@SuppressLint("ResourceAsColor")
class Circle(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    lateinit var paint: Paint
    lateinit var rect: RectF

    var startAngle = 0f


    var moveAngle = 0f

    var color = 0
    var colorPoint = 0
    var colorInnerPoint = 0
    var alpha = 100
    var strokeWidth = 40f
    var strokeBaseWidth = 0f

    var drawPoint = false
    var drawBasePoint = false
    var visible: Boolean = false
    var circleWidth = 0f
    var circleHeight = 0f


    init {
        //View의 배경색 지정
        setBackgroundColor(resources.getColor(R.color.white, null))
        paint = Paint()
        paint.isAntiAlias = true
        paint.strokeCap = Paint.Cap.ROUND

        var radius = strokeWidth / 2
        rect = RectF(radius, radius, circleWidth - radius, circleHeight - radius)
//        paint.style = Paint.Style.STROKE
//        paint.alpha = alpha


    }


    @SuppressLint("ResourceAsColor")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawArc(rect, startAngle, moveAngle, false, paint)

        if (!visible) return

        paint.style = Paint.Style.STROKE
        paint.color = R.color.point_green
        paint.strokeWidth = strokeWidth
        if (rect != null) {
            canvas?.drawArc(rect, startAngle, moveAngle, false, paint)
        }


        val radius = rect.width() / 2
        //원의 끝부분 포인트트
        if (drawPoint) {
            paint.style = Paint.Style.FILL
            paint.color = colorPoint
            val point: Point =
                calculatePointOnArc(rect.centerX(), rect.centerY(), radius, startAngle + moveAngle)
            canvas?.drawCircle(point.x.toFloat(), point.y.toFloat(), strokeWidth / 2, paint)
        }

        if (drawBasePoint) {
            paint.color = colorInnerPoint
            val point: Point =
                calculatePointOnArc(rect.centerX(), rect.centerY(), radius, startAngle + moveAngle)
            canvas?.drawCircle(point.x.toFloat(), point.y.toFloat(), strokeBaseWidth / 2, paint)
        }

    }


    private fun calculatePointOnArc(
        circleCeX: Float,
        circleCeY: Float,
        circleRadius: Float,
        endAngle: Float
    ): Point {

        val point = Point()
        val endAngleRadian: Double = endAngle * (Math.PI / 180)

        val pointX: Int = (circleCeX + circleRadius * cos(endAngleRadian)).roundToInt()
        val pointY: Int = (circleCeY + circleRadius * sin(endAngleRadian)).roundToInt()

        point.x = pointX
        point.y = pointY

        return point
    }


}