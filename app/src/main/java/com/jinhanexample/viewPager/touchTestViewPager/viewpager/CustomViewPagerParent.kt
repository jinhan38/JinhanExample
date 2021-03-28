package com.jinhanexample.viewPager.touchTestViewPager.viewpager

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.core.view.MotionEventCompat
import androidx.viewpager.widget.ViewPager

class CustomViewPagerParent : ViewPager {


    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)


    var isSwipeEnable = true

    companion object {
        private const val TAG = "CustomViewPagerParent"
    }


    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.d(TAG, "CustomViewPagerParent onInterceptTouchEvent: ")
        return isSwipeEnable
//        return if (isSwipeEnable) {
//            super.onInterceptTouchEvent(ev)
//        } else {
//            if (MotionEventCompat.getActionMasked(ev) == MotionEvent.ACTION_MOVE) {
//                // ignore move action
//            } else {
//                if (super.onInterceptTouchEvent(ev)) {
//                    super.onTouchEvent(ev)
//                }
//            }
//            false
//        }
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        Log.d(TAG, "CustomViewPagerParent onTouchEvent: ")
        return isSwipeEnable

//        return if (isSwipeEnable) {
//            super.onTouchEvent(ev)
//        } else {
//            MotionEventCompat.getActionMasked(ev) != MotionEvent.ACTION_MOVE && super.onTouchEvent(
//                ev)
//        }
    }


}