package com.jinhanexample.touch

import android.content.Context
import android.util.Log
import android.view.MotionEvent
import android.widget.FrameLayout

class CustomLayout(context : Context): FrameLayout(context) {

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d(Companion.TAG, "dispatchTouchEvent: 진입")
        return super.dispatchTouchEvent(ev)
    }

    /**
     * intercept 함수는 viewGroup만 가지고 있다
     * 여기서 true를 반납하면 해당 viewgroup의 childView/childLayout으로 터치 이벤트가 전달되지 않는다.
     * true값을 반환한다는 것은 터치 리스너를 child로부터 뺏어온다고 이해하면 된다.
     */
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.d(TAG, "onInterceptTouchEvent: 진입")
        return super.onInterceptTouchEvent(ev)
//        return true
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d(TAG, "onTouchEvent: 진입")
        return super.onTouchEvent(event)
    }

    companion object {
        private const val TAG = "1111 CustomLayout"
    }
}