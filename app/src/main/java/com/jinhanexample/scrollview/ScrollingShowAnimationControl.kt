package com.jinhanexample.scrollview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ScrollView

class ScrollingShowAnimationControl : ScrollView, ViewTreeObserver.OnGlobalLayoutListener {

    companion object{
        private const val TAG = "ScrollShowAnimation"
    }

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attr: AttributeSet?) : this(context, attr, 0)
    constructor(context: Context, attr: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attr,
        defStyleAttr
    ) {
        overScrollMode = OVER_SCROLL_NEVER
        viewTreeObserver.addOnGlobalLayoutListener(this)
    }

    var header: View? = null

    var fifth: View? = null
        set(value) {
            field = value
            field?.let {
                it.translationZ = 1f
//                it.setOnClickListener { _ ->
//                    //클릭 시, 헤더뷰가 최상단으로 오게 스크롤 이동
//                    this.smoothScrollTo(scrollX, it.top)
//                    callStickListener()
//                }
            }
        }

    var stickListener: (View) -> Unit = {}
    var freeListener: (View) -> Unit = {}

    private var mIsHeaderSticky = false

    private var mHeaderInitPosition = 0f
    private var fifthInitPosition = 0f

    override fun onGlobalLayout() {
        mHeaderInitPosition = header?.top?.toFloat() ?: 0f
        fifthInitPosition = fifth?.bottom?.toFloat() ?: 0f
        //1 dp * 3.5 = 3.5px
        //ex) view의 height가 200이면
        // 200 * 3.5 = 700px

    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        //스크롤할 때 값들은 px로 넘어온다
        // t가 현재 스크롤하고 있는 화면의 px값
        Log.d(TAG, "onScrollChanged: l : $l, t : $t, oldl : $oldl, oldt : $oldt")

        var percentScroll = this.scrollY / this.maxScrollAmount

        Log.d(TAG, "onScrollChanged: scrollY : ${this.scrollY},  퍼센트 : $percentScroll")


        
        Log.d(TAG, "onScrollChanged: $fifthInitPosition")


        val scrolly = t
        Log.d(TAG, "onScrollChanged: scrolly : $scrolly")

        if (scrolly > mHeaderInitPosition) {
            stickHeader(scrolly - mHeaderInitPosition)
        } else {
            freeHeader()
        }
    }

    private fun stickHeader(position: Float) {
        header?.translationY = position
        callStickListener()
    }

    private fun callStickListener() {
        if (!mIsHeaderSticky) {
            stickListener(header ?: return)
            mIsHeaderSticky = true
        }
    }

    private fun freeHeader() {
        header?.translationY = 0f
        callFreeListener()
    }

    private fun callFreeListener() {
        if (mIsHeaderSticky) {
            freeListener(header ?: return)
            mIsHeaderSticky = false
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        viewTreeObserver.removeOnGlobalLayoutListener(this)
    }

}