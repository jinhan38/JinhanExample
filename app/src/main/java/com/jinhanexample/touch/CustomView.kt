package com.jinhanexample.touch

import android.content.Context
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatTextView

class CustomView(context : Context) : AppCompatTextView(context) {

    //dispatchTouchEvent의 값을 false로 return하면 해당 뷰의 touchEvent가 발생하지 않는다.
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Log.d(TAG, "dispatchTouchEvent: 진입")
//        return super.dispatchTouchEvent(event)
        return false
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d(TAG, "onTouchEvent: 진입")
        return super.onTouchEvent(event)
    }

    companion object {
        private const val TAG = "1111 CustomView"
    }


}