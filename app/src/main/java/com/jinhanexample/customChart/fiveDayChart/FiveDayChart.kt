package com.jinhanexample.customChart.fiveDayChart

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.jinhanexample.Common
import com.jinhanexample.R

class FiveDayChart : View {

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(
        context: Context?,
        viewLeft: Float,
        viewTop: Float,
        viewRight: Float,
        viewBottom: Float,
        viewColor: Int
    ) : super(
        context
    ) {
        this.viewLeft = viewLeft
        this.viewTop = viewTop
        this.viewRight = viewRight
        this.viewBottom = viewBottom
        this.viewColor = viewColor

    }

    private var viewLeft = 0f
    private var viewTop = 0f
    private var viewRight = 0f
    private var viewBottom = 0f
    private var viewColor = 0

    init {
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //뷰 전체 layout 그리기
        val viewRect = RectF(viewLeft, viewTop, viewRight, viewBottom)
        val viewPaint = Paint()
        viewPaint.color = setColor(context, viewColor)
        viewPaint.style = Paint.Style.FILL
        canvas?.drawRect(viewRect, viewPaint)

//        http://www.javased.com/?api=android.graphics.DashPathEffect

        //차트 내 점선 그리기 6개
        setDotLine(viewRect, canvas)

        //y축 텍스트
        val viewTopY = viewRect.top
        val viewBottomY = viewRect.bottom
        //textView inflat


        //x축 텍스트

    }


    /**
     * 점선 6개 그리기
     */
    private fun setDotLine(viewRect: RectF, canvas: Canvas?) {

        val dotWidth = Common.getDP(context, 3)
        val dotPaint = Paint()
        dotPaint.isAntiAlias = true
        dotPaint.color = setColor(context, R.color.red)
        dotPaint.strokeWidth = dotWidth
        dotPaint.pathEffect = DashPathEffect(floatArrayOf(dotWidth, dotWidth * 2), 0f)
        //floatArrayOf의 첫번째는 점선의 width, 두번째는 점선의 간격
        //phase는 점선이 시작할 때 잘려나가는 이미지 값의 허용범위

        val dotOffset = (viewBottom - viewTop) / 5
        val startX = viewRect.left
        val stopX = viewRect.right

        //1~4번째 점선
        for (i in 1..4) {
            val startY = (dotOffset * i) - (dotWidth / 2) + viewTop
            canvas?.drawLine(startX, startY, stopX, startY, dotPaint)
        }

        //첫번째 점선
        val firstStartY = (dotWidth / 2) + viewTop
        canvas?.drawLine(startX, firstStartY, stopX, firstStartY, dotPaint)

        //여섯번째 점선
        val sixthStartY = viewRect.bottom - (dotWidth / 2) + viewTop
        canvas?.drawLine(startX, sixthStartY, stopX, sixthStartY, dotPaint)
    }

    /**
     * 컬러 넣기
     */
    private fun setColor(context: Context, color: Int): Int {
        return context.resources.getColor(color, null)
    }
}