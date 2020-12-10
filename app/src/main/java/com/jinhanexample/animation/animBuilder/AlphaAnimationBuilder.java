package com.jinhanexample.animation.animBuilder;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Interpolator;

public class AlphaAnimationBuilder {

    private AlphaAnimationBuilder(Builder builder) {
        View view = builder.view;
        float fromAlpha = builder.fromAlpha;
        float toAlpha = builder.toAlpha;
        int duration = builder.duration;
        Interpolator interpolator = builder.interpolator;
        int startOffset = builder.startOffset;
        boolean fillAfter = builder.fillAfter;
        boolean fillBefore = builder.fillBefore;
        int repeatMode = builder.repeatMode;
        int repeatCount = builder.repeatCount;


        if (interpolator == null) {
            interpolator = new AccelerateDecelerateInterpolator();
        }


        AlphaAnimation alphaAnimation = new AlphaAnimation(fromAlpha, toAlpha);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setInterpolator(interpolator);
        alphaAnimation.setStartOffset(startOffset);
        alphaAnimation.setFillAfter(fillAfter);
        alphaAnimation.setFillBefore(fillBefore);
        alphaAnimation.setRepeatMode(repeatMode);
        alphaAnimation.setRepeatCount(repeatCount);

        view.startAnimation(alphaAnimation);
    }


    public static class Builder {
        private View view;
        private float fromAlpha = 0;
        private float toAlpha = 0;
        private int duration = 0;
        private Interpolator interpolator = new AccelerateDecelerateInterpolator(); //default
        private int startOffset = 0;
        private boolean fillAfter = false;
        private boolean fillBefore = false;
        private int repeatMode = 0;
        private int repeatCount = 0;


        //builder 패턴을 종료하는 함수
        //현재의 클래스를 생성하면서 종료한다.
        public AlphaAnimationBuilder build() {
            return new AlphaAnimationBuilder(this);
        }


        //필수로 값이 필요한 속성들을 Builder의 생성자에 넣어주세요
        public Builder(View view, float fromAlpha, float toAlpha, int duration) {
            this.view = view;
            this.fromAlpha = fromAlpha;
            this.toAlpha = toAlpha;
            this.duration = duration;
        }

        // return 타입이 Builder다. 즉 현재 클래스를 반환하는 것이므로 계속 이 클래스에 존재한다.
        //때문에 종료하지 않고 . 를 이용해 계속 이어갈 수 있다.
        public Builder setInterpolator(Interpolator interpolator) {
            this.interpolator = interpolator;
            return this;
        }

        public Builder setStartOffset(int startOffset) {
            this.startOffset = startOffset;
            return this;
        }

        public Builder setFillAfter(boolean fillAfter) {
            this.fillAfter = fillAfter;
            return this;
        }

        public Builder setFillBefore(boolean fillBefore) {
            this.fillBefore = fillBefore;
            return this;
        }

        public Builder setRepeatMode(int repeatMode) {
            this.repeatMode = repeatMode;
            return this;
        }

        public Builder setRepeatCount(int repeatCount) {
            this.repeatCount = repeatCount;
            return this;
        }


    }


}
