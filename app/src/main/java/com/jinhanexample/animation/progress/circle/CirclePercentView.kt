package com.jinhanexample.animation.progress.circle

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.compose.ui.graphics.Color
import com.jinhanexample.R
import kotlinx.android.synthetic.main.layout_circle_percent.view.*


// 배경이 되는 circle과 animation이 진행될 circle, 두개의 circle을 가지고 있는 View
@SuppressLint("ResourceAsColor")
class CirclePercentView(context: Context, attributeSet: AttributeSet) :
    FrameLayout(context, attributeSet) {

    var circleAnimation: CircleAnimation
    private var circleItem: Circle

    init {
        setBackgroundColor(R.color.white)

        val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        this.addView(inflater.inflate(R.layout.layout_circle_percent, null))



        circleItem = findViewById(R.id.circleItem)

        circleAnimation = CircleAnimation(
            circleItem, 120f, 300f, R.color.brand_purple, 255,
            bLine = true,
            showEndCircle = true
        )

    }
    fun showBaseCircle(){
        val circleBase = findViewById<Circle>(R.id.circleBase)
        circleBase.visible = true
        circleBase.startAngle = 0f
        circleBase.moveAngle = 360f
        circleBase.color = R.color.brand_red
        circleBase.strokeWidth = 40f
        circleBase.requestLayout() // 찾아보기
    }

    fun startAnimation() {
        //circle 클래스는 View를 상속받았고, View 클래스에 startAnimation 함수 존재
        circleItem.startAnimation(circleAnimation)
    }


}