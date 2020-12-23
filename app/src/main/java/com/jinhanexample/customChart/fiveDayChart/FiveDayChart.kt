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

    constructor(context: Context?, viewWidth: Float, viewHeight: Float, viewColor: Int) : super(
        context
    ) {
        this.viewWidth = viewWidth
        this.viewHeight = viewHeight
        this.viewColor = viewColor

    }

    private var viewWidth = 0f
    private var viewHeight = 0f
    private var viewColor = 0

    init {
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //뷰 전체 layout 그리기
        val viewRect = RectF(0f, 0f, viewWidth, viewHeight)
        val viewPaint = Paint()
        viewPaint.color = setColor(context, viewColor)
        viewPaint.style = Paint.Style.FILL
        canvas?.drawRect(viewRect, viewPaint)

//        http://www.javased.com/?api=android.graphics.DashPathEffect

        //차트 내 점선 그리기 6개
        val dotWidth = Common.getDP(context, 3)
        val dotPaint = Paint()
        dotPaint.isAntiAlias = true
        dotPaint.color = setColor(context, R.color.red)
        dotPaint.strokeWidth = dotWidth
        dotPaint.pathEffect = DashPathEffect(floatArrayOf(dotWidth, dotWidth * 2), 0f)
        //floatArrayOf의 첫번째는 점선의 width, 두번째는 점선의 간격
        //phase는 점선이 시작할 때 잘려나가는 이미지 값


        val dotOffset = viewHeight / 5
        val startX = viewRect.left
        val stopX = viewRect.right


        //첫번째
        val firstStartY = (dotWidth / 2)
        canvas?.drawLine(startX, firstStartY, stopX, firstStartY, dotPaint)

        //두번째
        val secondStartY = dotOffset - (dotWidth / 2)
        canvas?.drawLine(startX, secondStartY, stopX, secondStartY, dotPaint)

        //세번째
        val thirdStartY = (dotOffset * 2) - (dotWidth / 2)
        canvas?.drawLine(startX, thirdStartY, stopX, thirdStartY, dotPaint)

        //네번째
        val fourthStartY = (dotOffset * 3) - (dotWidth / 2)
        canvas?.drawLine(startX, fourthStartY, stopX, fourthStartY, dotPaint)

        //다섯번째
        val fifthStartY = (dotOffset * 4) - (dotWidth / 2)
        canvas?.drawLine(startX, fifthStartY, stopX, fifthStartY, dotPaint)

        //여섯번째
        val sixthStartY = viewRect.bottom - (dotWidth / 2)
        canvas?.drawLine(startX, sixthStartY, stopX, sixthStartY, dotPaint)
    }


    /**
     * 컬러 넣기
     */
    private fun setColor(context: Context, color: Int): Int {
        return context.resources.getColor(color, null)
    }
}