package com.jinhanexample.customChart.fiveDayChart

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import com.jinhanexample.R
import kotlinx.android.synthetic.main.candle_chart_marker.view.*

class FiveDayChartImage : View {

    //범위 배경
    //https://stackoverflow.com/questions/15450328/androidplot-background-and-ranges


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

    var circleLocationArray = ArrayList<CircleLocation>()
    private val paddingValue = getDP(context, 5)

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //뷰 전체 layout 그리기
        val viewRect = RectF(0f, 0f, viewWidth, viewHeight) //view의 영역
        val viewPaint = Paint()
        viewPaint.color = setColor(context, viewColor)
        viewPaint.style = Paint.Style.FILL
        canvas?.drawRect(viewRect, viewPaint)

        //차트 내 점선 그리기 6개
//        setCircleDashLine(viewRect, canvas)
        setRectDashLine(viewRect, canvas)

        //배경색 지정
        setPointLocation(canvas, viewRect)

        //차트 내 점 찍기
        drawCirclePoint(canvas, viewRect)
    }


    /**
     * 사각형 점선 6개 그리기
     */
    private fun setRectDashLine(viewRect: RectF, canvas: Canvas?) {

        val dashWidth = getDP(context, 1)
        val dotPaint = Paint()
        dotPaint.isAntiAlias = true
        dotPaint.color = setColor(context, dashColor)
        dotPaint.style = Paint.Style.STROKE // style Stroke로 설정해야 점선 적용 가능
        dotPaint.strokeWidth = dashWidth // strokeWidth값은 선의 두께
        dotPaint.pathEffect = DashPathEffect(floatArrayOf(dashWidth, dashWidth * 5), 0f)
//        dotPaint.strokeCap = Paint.Cap.ROUND
        //DashPathEffect의 첫번째 인자 interval은 점선의 width(dash의 길이)와 점선의 간격(dash의 간격) 입력
        //phase는 점선이 시작할 때 잘려나가는 이미지 값의 허용범위


        val dotOffset = (viewHeight - paddingValue) / 5
        val startX = viewRect.left
        val stopX = viewRect.right

        //첫번째 점선
        val firstStartY = (dashWidth / 2) + paddingValue
        canvas?.drawLine(startX, firstStartY, stopX, firstStartY, dotPaint)

        //2~6번째 점선
        for (i in 1..5) {
            val startY = (dotOffset * i) - (dashWidth / 2) + paddingValue
            canvas?.drawLine(startX, startY, stopX, startY, dotPaint)
        }

    }


    /**
     * 원형 점선 6개 그리기
     */
    private fun setCircleDashLine(viewRect: RectF, canvas: Canvas?) {
        //원형 점을 그렸을 때 이미지가 짤리는 부분 때문에 padding 5을 주었다.
        //때문에 원형 점의 위치와 line을 맞추기 위해 점선에도 padding 5을 적용시켰다.
        val viewHeight = viewRect.height() - (paddingValue * 2)

        val dashWidth = getDP(context, 1) //점의 크기
        val paint = Paint()
        paint.isAntiAlias = true
        paint.color = setColor(context, dashColor)
        paint.strokeWidth = dashWidth
        paint.style = Paint.Style.STROKE

        val phase = 0f //첫번째 모양(원형 점선)이 찍히기 전의, 시작점으로부터의 간격
        val advance = getDP(context, 10) //점선의 간격
        val style: PathDashPathEffect.Style = PathDashPathEffect.Style.MORPH
        val path = Path()
        val pathShape = Path()
        //시작 x좌표, 시작 y좌표
//        pathShape.addCircle(0f, 0f, dashWidth, Path.Direction.CCW)
        pathShape.addOval(0f, 0f, dashWidth, dashWidth, Path.Direction.CCW)

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
        val radius = getDP(context, 2)
        val circlePaint = Paint()
        circlePaint.isAntiAlias = true
        circlePaint.color = setColor(context, R.color.black)
        circlePaint.style = Paint.Style.FILL

        val perX: Float = (viewRect.width() - (paddingValue * 2)) / 4
        val perY: Float = (viewHeight - paddingValue) / 100

        val xArrayList = ArrayList<Float>()

        for (i in 0 until scoreArray.size) {

            val x: Float = (perX * i) + paddingValue
            val y: Float = viewRect.bottom - (scoreArray[i] * perY)
            xArrayList.add(x)

            if (scoreArray[i] != 0) {

                if (i == scoreArray.size - 1) {
                    //마지막 포지션에서 원 하나 더 추가하고 컬러 변경하기기
                    canvas?.drawCircle(
                        (perX * i) + paddingValue,
                        viewRect.bottom - (scoreArray[i] * perY),
                        getDP(context, 5),
                        circlePaint
                    )
                    circlePaint.color = setColor(context, R.color.white)

                }

                canvas?.drawCircle(x, y, radius, circlePaint)
            }

        }

        (context as FiveDayChartActivity).initXAxisText(xArrayList)

    }

    private fun setPointLocation(canvas: Canvas?, viewRect: RectF) {

        val perX: Float = (viewRect.width() - (paddingValue * 2)) / 4
        val perY: Float = (viewHeight - paddingValue) / 100
        var x = 0f
        for (i in 0 until scoreArray.size) {

            if (scoreArray[i] != 0) {

                x = (perX * i) + paddingValue
                val y: Float = viewRect.bottom - (scoreArray[i] * perY)


                if (circleLocationArray.size == 0) { // 시작점은 차트 왼쪽 하단부분부터
                    circleLocationArray.add(CircleLocation(x, viewRect.bottom))
                    circleLocationArray.add(CircleLocation(x, y))
                }

                circleLocationArray.add(CircleLocation(x, y))

//                if (i == scoreArray.size - 1) { //마지막 점을 차트 오른쪽 하단에서 시작하여 시작점으로 돌아오기
//                    circleLocationArray.add(CircleLocation(x, viewRect.bottom))
//                }
            }

        }

        circleLocationArray.add(CircleLocation(x, viewRect.bottom)) //마지막 점의 하단부
        setBackgroundColor(viewRect, circleLocationArray, canvas)
    }


    //차트 안쪽 배경 지정
    private fun setBackgroundColor(
        viewRect: RectF,
        circleLocationArray: ArrayList<CircleLocation>,
        canvas: Canvas?
    ) {


        val colors = intArrayOf(
            Color.parseColor("#1A73BAF8"),
            Color.parseColor("#1A8E88CC"),
            Color.parseColor("#1Aff5f5f")
        )


        val paint = Paint()
        paint.isAntiAlias = true
        paint.shader = LinearGradient(
            0f, 0f, 0f, measuredHeight.toFloat(),
            colors,
            null,
            Shader.TileMode.CLAMP
        )
        paint.style = Paint.Style.FILL
        val path = Path()

        path.reset() //path 시작
        path.moveTo(viewRect.left, viewRect.bottom) //점선의 시작 좌표
        for (i in 0 until circleLocationArray.size) {
            path.lineTo(circleLocationArray[i].x, circleLocationArray[i].y)
        }
        path.close() //path 끝

        canvas?.drawPath(path, paint)

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