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
class Circle(
    context: Context, attributeSet: AttributeSet, width: Int, height: Int
) : View(context, attributeSet) {


    
    lateinit var paint: Paint
    lateinit var rect: RectF

    var startAngle = 0f
    var moveAngle = 0f

    var color = R.color.black
    var colorPoint = R.color.black
    var colorInnerPoint = R.color.black
    var strokeWidth = 40f
    var strokeInnerPointWidth = 20f

    var bDrawPoint = false
    var bDrawInnerPoint = false
    var visible: Boolean = false


    init {
        //View의 배경색 지정
        setBackgroundColor(resources.getColor(R.color.white, null))
        paint = Paint()
        paint.isAntiAlias = true
        paint.strokeCap = Paint.Cap.ROUND

        var nRadius = strokeWidth / 2
        rect = RectF(nRadius, nRadius, width - nRadius, height - nRadius)


    }


    @SuppressLint("ResourceAsColor")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        if (!visible) return
        canvas?.drawArc(rect, startAngle, moveAngle, false, paint)

        paint.style = Paint.Style.STROKE
        paint.color = color
        paint.strokeWidth = strokeWidth
        canvas?.drawArc(rect, startAngle, moveAngle, false, paint)


        val radius = rect.width() / 2
        //원의 끝부분 포인트트
        if (bDrawPoint) {
            paint.style = Paint.Style.FILL
            paint.color = colorPoint
            val point: Point =
                calculatePointOnArc(rect.centerX(), rect.centerY(), radius, startAngle + moveAngle)
            canvas?.drawCircle(point.x.toFloat(), point.y.toFloat(), strokeWidth / 2, paint)
        }

        if (bDrawInnerPoint) {
            paint.color = colorInnerPoint
            val point: Point =
                calculatePointOnArc(rect.centerX(), rect.centerY(), radius, startAngle + moveAngle)
            canvas?.drawCircle(point.x.toFloat(), point.y.toFloat(), strokeInnerPointWidth / 2, paint)
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