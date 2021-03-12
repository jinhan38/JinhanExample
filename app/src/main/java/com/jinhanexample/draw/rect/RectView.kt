package com.jinhanexample.draw.rect

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import com.jinhanexample.R


//View를 상속받아주세요
class RectView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    lateinit var title: TextView


    //Draw는 그림판에 그림을 그리는 클래스입니다.
    //Draw 클래스를 통해 canvas에 paint와 rect를 이용해 그림을 그려보겠습니다.
    //또한 텍스트뷰를 만들어서 TouchEvent를 통해 이동시켜보겠습니다.
    @SuppressLint("ResourceAsColor", "DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        //녹색 사각형
        val green = Paint()
        green.style = Paint.Style.FILL // Fill은 View를 채우는 속성
        green.color = resources.getColor(R.color.point_green, null)//paint의 컬러 지정
        var greenRect = RectF()
        greenRect.set(300f, 300f, 800f, 800f)
        canvas?.drawRect(greenRect, green)
        //rect는 사각형을 그리는클래스입니다.
        //값의 순서는 왼쪽, 위쪽, 오른쪽 ,아래쪽입니다.
        //계산 방법은 왼쪽과 오른쪽은 화면의 왼쪽을 기준으로 한 거리,
        // 위쪽과 아래쪽은 화면의 위쪽을 기준으로 한 거리라고 생각하면 됩니다.
        // 왼쪽이 300, 오른쪽이 800이니 두 값을 뺀 500이 사각형의 width입니다.


        val orange = Paint()
        orange.style = Paint.Style.STROKE  //테두리를 그리는 속성
        orange.strokeWidth = 20f // 테두리 두께 20
        orange.color = resources.getColor(R.color.point_orange, null)
        var orangeRect = RectF()
        orangeRect.set(300f, 300f, 800f, 800f)
        canvas?.drawRect(orangeRect, orange)
        //종이 위에 계속 덧칠하듯이 canvas.drawRect를 하면 그 위에 덧칠하듯 그림이 그려집니다.
        //orange색의 사각형 테두리를 그렸습니다.


        //drawAcr는 rect의 안에 원을 그리는 클래스입니다.
        val red = Paint()
        red.style = Paint.Style.STROKE
        red.strokeWidth = 20f
        red.color = resources.getColor(R.color.point_blue, null)
        var circleRect = RectF()
        circleRect.set(320f, 320f, 780f, 780f)
        //orange 테두리의 안쪽에 그리기 위해 왼쪽과 위쪽의 값은 20씩 증가시켰고
        // 오른쪽과 아래쪽의 값은 20씩 감소시켰습니다.
        canvas?.drawArc(circleRect, 0f, 270f, false, red)
        //startAngle은 원의 시작 지점으로 0은 오른쪽입니다.
        // +360은 startAngle에서 시작하여 시계방향으로 300도를 그립니다.


        //텍스트뷰를 그려보겠습니다.
        title = TextView(context)
        title.text = "Draw 연습"
        title.textSize = 20f
        title.setTextColor(resources.getColor(R.color.black, null))
        title.gravity = Gravity.CENTER_HORIZONTAL

        //LayoutParams를 이용해 layout을 설정하겠습니다.
        //width = WRAP_CONTENT,  height = WRAP_CONTENT
        var params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ) //activity_rect 에서 RectView의 부모 부가 FrameLayout입니다.
        title.layoutParams = params
        //title의 layoutParams에 설정한 후 parent View에 추가해주세요
        (this.parent as FrameLayout).addView(title)


    }

    //Touch Event를 이용해 textView를 움직여보겠습니다.
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        //화면을 눌렀을 때 event가 동작하도록 했습니다.

        if (event != null) {
            Log.d(TAG, "onTouchEvent:  event.x : " + event.y)
        }
        //그리고 해당 지점의 좌표값도 얻어올 수 있습니다.
        when (event?.action) {

            //ACTION_DOWN = 화면을 눌렀을 때
            MotionEvent.ACTION_DOWN -> {
                title.x = event.x
                title.y = event.y
                Toast.makeText(context, "클릭", Toast.LENGTH_SHORT).show()
            }

            //ACTION_MOVE = 화면을 드래그할 때
            MotionEvent.ACTION_MOVE -> {
                title.x = event.x
                title.y = event.y
            }
        }
        return true
    }


}