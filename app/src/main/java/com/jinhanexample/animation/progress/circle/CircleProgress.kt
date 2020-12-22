package com.jinhanexample.animation.progress.circle

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.jinhanexample.R
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@SuppressLint("ResourceAsColor")
class CircleProgress : View {


    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(
        context: Context?,
        rectWidth: Float,
        rectHeight: Float,
        color: Int,
        baseColor: Int
    ) : super(context) {
        this.rectWidth = rectWidth
        this.rectHeight = rectHeight
        this.color = color
        this.baseColor = baseColor
    }

    var rectWidth: Float = 0f
    var rectHeight: Float = 0f
    var startAngle = 0f
    var endAngle = 0f
    var baseStartAngle = 0f
    var baseEndAngle = 0f

    var color = 0
    var baseColor = 0
    var endPointColor = R.color.brand_blue
    var colorInnerPoint = R.color.white
    var strokeWidth = 80f
    var strokeInnerPointWidth = 40f

    var visible: Boolean = false


    init {
        //View의 배경색 지정
        setBackgroundColor(resources.getColor(R.color.white, null))
    }


    @SuppressLint("ResourceAsColor", "DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (!visible) return


        val marginValue = strokeWidth / 2

        //progress 배경
        //left와 top에 strokeWidth/2 값을 준 이유는  stroke의 값 안쪽으로 커지는 것이 아니라
        //left와 top을 기준으로 stroke 값 만큼 두께가 양쪽으로 넓어지기 때문에 화면에 짤려 보이기 때문입니다.
        val baseRect = RectF(
            marginValue,
            marginValue,
            rectWidth - marginValue,
            rectHeight - marginValue
        )

        val basePaint = Paint()
        basePaint.style = Paint.Style.STROKE
        basePaint.color = resources.getColor(baseColor, null)
        basePaint.strokeCap = Paint.Cap.ROUND
        basePaint.strokeWidth = strokeWidth / 2
        canvas?.drawArc(baseRect, baseStartAngle, baseEndAngle, false, basePaint)


        //progress percent
        val rect =
            RectF(marginValue, marginValue, rectWidth - marginValue, rectHeight - marginValue)

        val paint = Paint()
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        paint.color = resources.getColor(color, null)
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = strokeWidth
        canvas?.drawArc(rect, startAngle, endAngle, false, paint)


        //progress의 끝부분에 점 찍기
        //큰 원
        val radius = rect.width() / 2
        val endPointPaint = Paint()
        endPointPaint.style = Paint.Style.FILL
        endPointPaint.color = resources.getColor(endPointColor, null)
        val pointBig: Point =
            calculatePointOnArc(rect.centerX(), rect.centerY(), radius, startAngle + endAngle)
        canvas?.drawCircle(
            pointBig.x.toFloat(),
            pointBig.y.toFloat(),
            strokeWidth / 2,
            endPointPaint
        )

        //작은 원
        endPointPaint.color = resources.getColor(colorInnerPoint, null)
        val pointSmall: Point =
            calculatePointOnArc(rect.centerX(), rect.centerY(), radius, startAngle + endAngle)
        canvas?.drawCircle(
            pointSmall.x.toFloat(),
            pointSmall.y.toFloat(),
            strokeInnerPointWidth / 2 - 10,
            endPointPaint
        )
    }


    /**
     * 원의 끝 지점의 좌표 구하기
     */
    private fun calculatePointOnArc(
        centerX: Float,
        centerY: Float,
        circleRadius: Float,
        endAngle: Float
    ): Point {

        val point = Point()
        val endAngleRadian: Double = endAngle * (Math.PI / 180)

        val pointX: Int = (centerX + circleRadius * cos(endAngleRadian)).roundToInt()
        val pointY: Int = (centerY + circleRadius * sin(endAngleRadian)).roundToInt()

        Log.d("TAG", "calculatePointOnArc: pointX : $pointX ")
        Log.d("TAG", "calculatePointOnArc: pointY : $pointY ")

        point.x = pointX
        point.y = pointY

        return point
    }
}


