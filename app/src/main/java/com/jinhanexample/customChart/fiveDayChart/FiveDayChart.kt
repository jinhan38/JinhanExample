package com.jinhanexample.customChart.fiveDayChart

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.PathShape
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.graphics.TileMode
import com.jinhanexample.Common
import com.jinhanexample.R

class FiveDayChart : View {

    //범위 배경
    //https://stackoverflow.com/questions/15450328/androidplot-background-and-ranges


    //TODO:: padding 주기, 점이 짤려보인다
    //TODO:: 범위 background 설정하기
    //TODO:: X축, Y 축에 텍스트 넣기

    companion object {
        private const val TAG = "FiveDayChart"
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)


    constructor(
        context: Context?,
        viewWidth: Float,
        viewHeight: Float,
        viewColor: Int,
        dashColor: Int,
        scoreArray: ArrayList<Int>
    ) : super(
        context
    ) {
        this.viewWidth = viewWidth
        this.viewHeight = viewHeight
        this.viewColor = viewColor
        this.scoreArray = scoreArray
        this.dashColor = dashColor
    }


    private var viewWidth = 0f
    private var viewHeight = 0f
    private var viewColor = 0 //배경 컬러

    private var dashColor = 0 //점선의 컬러

    private var scoreArray: ArrayList<Int> = ArrayList()//차트에 있는 점의 좌표

    private var circleLocationArray = ArrayList<CircleLocation>()

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //뷰 전체 layout 그리기
        val viewRect = RectF(0f, 0f, viewWidth, viewHeight) //view의 영역
        val viewPaint = Paint()
        viewPaint.color = setColor(context, viewColor)
        viewPaint.style = Paint.Style.FILL
        canvas?.drawRect(viewRect, viewPaint)

//        http://www.javased.com/?api=android.graphics.DashPathEffect


        //차트 내 점선 그리기 6개
        setCircleDashLine(viewRect, canvas)

        //배경색 지정
        setBackgroundPoint(canvas, viewRect)

        //차트 내 점 찍기
        drawCirclePoint(canvas, viewRect)
    }


    /**
     * 사각형 점선 6개 그리기
     */
    private fun setRectDashLine(viewRect: RectF, canvas: Canvas?) {

        val dashWidth = getDP(context, 3)
        val dotPaint = Paint()
        dotPaint.isAntiAlias = true
        dotPaint.color = setColor(context, dashColor)
        dotPaint.style = Paint.Style.STROKE
        dotPaint.strokeWidth = dashWidth
        dotPaint.pathEffect = DashPathEffect(floatArrayOf(dashWidth, dashWidth * 2), 0f)
        //floatArrayOf의 첫번째는 점선의 width, 두번째는 점선의 간격
        //phase는 점선이 시작할 때 잘려나가는 이미지 값의 허용범위

        val dotOffset = viewHeight / 6
        val startX = viewRect.left
        val stopX = viewRect.right

        //첫번째 점선
        val firstStartY = (dashWidth / 2)
        canvas?.drawLine(startX, firstStartY, stopX, firstStartY, dotPaint)

        //2~6번째 점선
        for (i in 1..5) {

            val startY = (dotOffset * i) - (dashWidth / 2)
            canvas?.drawLine(startX, startY, stopX, startY, dotPaint)
        }

    }

    /**
     * 원형 점선 6개 그리기
     */
    private fun setCircleDashLine(viewRect: RectF, canvas: Canvas?) {
        //원형 점을 그렸을 때 이미지가 짤리는 부분 때문에 padding 10을 주었다.
        //때문에 원형 점의 위치와 line을 맞추기 위해 점선에도 padding 10을 적용시켰다.
        val paddingValue = getDP(context, 10)
        val viewHeight = viewRect.height() - (paddingValue * 2)

        val dashWidth = getDP(context, 1) //점의 크기
        val paint = Paint()
        paint.isAntiAlias = true
        paint.color = setColor(context, dashColor)
        paint.strokeWidth = dashWidth
        paint.style = Paint.Style.STROKE

        val phase = 0f
        val advance = getDP(context, 10) //점선의 간격
        val style: PathDashPathEffect.Style = PathDashPathEffect.Style.TRANSLATE
        val path = Path()
        val pathShape = Path()
        //시작 x좌표, 시작 y좌표
        pathShape.addCircle(0f, 0f, dashWidth, Path.Direction.CCW)

        path.reset() //path 시작
        path.moveTo(viewRect.left, paddingValue) //점선의 시작 좌표
        path.lineTo(viewRect.right, paddingValue) //점선의 끝 좌표
        path.close() //path 끝

        val pathDashPathEffect = PathDashPathEffect(pathShape, advance, phase, style)
        paint.pathEffect = pathDashPathEffect
        canvas?.drawPath(path, paint)

        val dotOffset = viewHeight / 5  //점선 사이의 Y값 간격

        //2~6번째 점선
        for (i in 1..5) {
            var y = dotOffset * i
            path.reset() //path 시작
//            if (i == 5) y -= 5
            path.moveTo(viewRect.left, y + paddingValue) //점선의 시작 좌표
            path.lineTo(viewRect.right, y + paddingValue) //점선의 끝 좌표
            path.close() //path 끝

            val pathDashPathEffect = PathDashPathEffect(pathShape, advance, phase, style)
            paint.pathEffect = pathDashPathEffect
            canvas?.drawPath(path, paint)

        }


    }


    private fun drawCirclePoint(canvas: Canvas?, viewRect: RectF) {
        //점 좌표 값에 padding값을 계산하여 넣었다.
        //그렇지 않으면 끝부분에 있을 경우 점이 짤린다.
        val paddingValue = getDP(context, 10)
        val radius = getDP(context, 4)
        val circlePaint = Paint()
        circlePaint.isAntiAlias = true
        circlePaint.color = setColor(context, R.color.black)
        circlePaint.style = Paint.Style.FILL

        val perX: Float = (viewRect.width() - (paddingValue * 2)) / 4
        val perY: Float = (viewRect.height() - (paddingValue * 2)) / 100

        for (i in 0 until scoreArray.size) {

            val x: Float = (perX * i) + paddingValue
            val y: Float = viewRect.bottom - (scoreArray[i] * perY) - paddingValue

            if (i == scoreArray.size - 1) {
                //마지막 포지션에서 원 하나 더 추가하고 컬러 변경하기기
                canvas?.drawCircle(
                    (perX * i) + paddingValue,
                    viewRect.bottom - (scoreArray[i] * perY) - paddingValue,
                    getDP(context, 10),
                    circlePaint
                )
                circlePaint.color = setColor(context, R.color.white)

            }

            canvas?.drawCircle(x, y, radius, circlePaint)

        }

    }


    private fun setBackgroundPoint(canvas: Canvas?, viewRect: RectF) {
        val paddingValue = getDP(context, 10)

        val perX: Float = (viewRect.width() - (paddingValue * 2)) / 4
        val perY: Float = (viewRect.height() - (paddingValue * 2)) / 100

        for (i in 0 until scoreArray.size) {

            var x: Float = (perX * i) + paddingValue
            var y: Float = viewRect.bottom - (scoreArray[i] * perY) - paddingValue


            if (i == 0) {
                circleLocationArray.add(CircleLocation(viewRect.left, viewRect.bottom))
                circleLocationArray.add(CircleLocation(viewRect.left, y))
            }

            circleLocationArray.add(CircleLocation(x, y))

            if (i == scoreArray.size - 1) {
                circleLocationArray.add(CircleLocation(viewRect.right, y))
                circleLocationArray.add(CircleLocation(viewRect.right, viewRect.bottom))
            }
        }

        setBackgroundColor(viewRect, circleLocationArray, canvas)
    }


    private fun setBackgroundColor(
        viewRect: RectF,
        circleLocationArray: ArrayList<CircleLocation>,
        canvas: Canvas?
    ) {

        val colors = intArrayOf(
            Color.parseColor("#738af8"),
            Color.parseColor("#8e88cc"),
            Color.parseColor("#2cff5f5f")
        )


        val paint = Paint()
        paint.isAntiAlias = true
        paint.shader = LinearGradient(
            0f, 0f, 0f, measuredHeight.toFloat(),
            colors,
            null,
            Shader.TileMode.CLAMP
        )
//        paint.color = setColor(context, R.color.brand_pink)
        paint.style = Paint.Style.FILL
        val path = Path()

        path.reset() //path 시작
        path.moveTo(viewRect.left, viewRect.bottom) //점선의 시작 좌표
        for (i in 0 until circleLocationArray.size) {
            path.lineTo(circleLocationArray[i].x, circleLocationArray[i].y)
        }
        path.close() //path 끝

//        paint.shader = LinearGradient(
//            0f,
//            0f,
//            0f,
//            15f,
//            0xFF1F9928.toInt(),
//            0xFF184F1E.toInt(),
//            Shader.TileMode.MIRROR
//        )
        canvas?.drawPath(path, paint)
//        val linearGradient: Shader = LinearGradient(colors, 0, 0, 100, 0, TileMode.Clamp)
//        paint.setShader(linearGradient)
//        Pnt.setShader(new LinearGradient (0, 0, 100, 0, Color.BLUE, Color.WHITE, TileMode.CLAMP));


    }


    /**
     * 컬러 넣기
     */
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

    data class CircleLocation(var x: Float, var y: Float)
}