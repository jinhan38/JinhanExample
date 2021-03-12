package com.jinhanexample.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

public class StickyNestedView extends NestedScrollView implements ViewTreeObserver.OnGlobalLayoutListener {
    public StickyNestedView(@NonNull Context context) {
        super(context);
    }

    public StickyNestedView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StickyNestedView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setOverScrollMode(OVER_SCROLL_NEVER); //스크롤 끝에 도달했을 때 물결표시 없애기
        getViewTreeObserver().addOnGlobalLayoutListener(this);

    }

    public View header = null;

    public void setHeader(View header) {
        this.header = header;
        this.header.setTranslationZ(1f);

        //클릭 시, 헤더뷰가 최상단으로 오게 스크롤 이동
//       this.header.setOnClickListener(view -> {
//            this.smoothScrollTo(getScrollX(), this.header.getTop());
//            callStickListener();
//        });
    }


    private boolean mIsHeaderSticky = false;
    private float mHeaderInitPosition = 0f;

    @Override
    public void onGlobalLayout() {
        mHeaderInitPosition = (float) this.header.getTop();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        int scrollY = t;

        if (scrollY > mHeaderInitPosition) {
            stickHeader(scrollY - mHeaderInitPosition);
        } else {
            freeHeader();
        }

    }

    private void stickHeader(float position) {
        this.header.setTranslationY(position);
        callStickListener();
    }

    private void callStickListener() {

        if (mIsHeaderSticky) {
            stickListener(this.header);
            mIsHeaderSticky = false;
        }
    }

    private void stickListener(View header) {
        if (header == null) {
            return;
        }
    }

    private void freeHeader() {
        this.header.setTranslationY(0f);
        callFreeListener();
    }

    private void callFreeListener() {
        if (mIsHeaderSticky) {
            freeListener(this.header);
            mIsHeaderSticky = false;
        }
    }

    private void freeListener(View header) {
        if (header == null) {
            return;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }
}
