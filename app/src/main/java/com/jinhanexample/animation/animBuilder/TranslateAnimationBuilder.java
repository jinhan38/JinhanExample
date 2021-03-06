package com.jinhanexample.animation.animBuilder;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;

public class TranslateAnimationBuilder {


    private TranslateAnimationBuilder(Builder builder) {
        View view = builder.view;
        int fromXType = builder.fromXType;
        int toXType = builder.toXType;
        int fromYType = builder.fromYType;
        int toYType = builder.toYType;
        float fromX = builder.fromX;
        float toX = builder.toX;
        float fromY = builder.fromY;
        float toY = builder.toY;
        int duration = builder.duration;
        Interpolator interpolator = builder.interpolator;
        int startOffset = builder.startOffset;
        boolean fillAfter = builder.fillAfter;
        boolean fillBefore = builder.fillBefore;
        boolean fillEnabled = builder.fillEnabled;
        int repeatMode = builder.repeatMode;
        int repeatCount = builder.repeatCount;
        Animation.AnimationListener listener = builder.listener;


        TranslateAnimation translateAnimation =
                new TranslateAnimation(fromXType, fromX, toXType, toX, fromYType, fromY, toYType, toY);
        translateAnimation.setDuration(duration);
        translateAnimation.setInterpolator(interpolator);
        translateAnimation.setStartOffset(startOffset);
        translateAnimation.setFillAfter(fillAfter);
        translateAnimation.setFillBefore(fillBefore);
        translateAnimation.setFillEnabled(fillEnabled);
        translateAnimation.setRepeatMode(repeatMode);
        translateAnimation.setRepeatCount(repeatCount);
        if (listener != null) translateAnimation.setAnimationListener(listener);

        view.startAnimation(translateAnimation);
    }

    public static class Builder {
        private final View view;
        private final int fromXType;
        private final float fromX;
        private final int toXType;
        private final float toX;
        private final int fromYType;
        private final float fromY;
        private final int toYType;
        private final float toY;
        private final int duration;
        private Interpolator interpolator = new AccelerateDecelerateInterpolator(); //default
        private int startOffset = 0;
        private boolean fillAfter = false;
        private boolean fillBefore = false;
        private boolean fillEnabled = false;
        private int repeatMode = 0;
        private int repeatCount = 0;
        Animation.AnimationListener listener = null;

        public TranslateAnimationBuilder build() {
            return new TranslateAnimationBuilder(this);
        }

        //필수로 값이 필요한 속성들을 Builder의 생성자에 넣어주세요
        public Builder(View view, int fromXType, float fromX, int toXType, float toX,
                       int fromYType, float fromY, int toYType, float toY, int duration) {
            this.view = view;
            this.fromXType = fromXType;
            this.fromX = fromX;
            this.toXType = toXType;
            this.toX = toX;
            this.fromYType = fromYType;
            this.fromY = fromY;
            this.toYType = toYType;
            this.toY = toY;
            this.duration = duration;
        }

        public TranslateAnimationBuilder.Builder setInterpolator(Interpolator interpolator) {
            this.interpolator = interpolator;
            return this;
        }

        public TranslateAnimationBuilder.Builder setStartOffset(int startOffset) {
            this.startOffset = startOffset;
            return this;
        }

        public TranslateAnimationBuilder.Builder setFillAfter(boolean fillAfter) {
            this.fillAfter = fillAfter;
            return this;
        }

        public TranslateAnimationBuilder.Builder setFillBefore(boolean fillBefore) {
            this.fillBefore = fillBefore;
            return this;
        }

        public TranslateAnimationBuilder.Builder setFillEnabled(boolean fillEnabled) {
            this.fillEnabled = fillEnabled;
            return this;
        }

        public TranslateAnimationBuilder.Builder setRepeatMode(int repeatMode) {
            this.repeatMode = repeatMode;
            return this;
        }

        public TranslateAnimationBuilder.Builder setRepeatCount(int repeatCount) {
            this.repeatCount = repeatCount;
            return this;
        }

        public TranslateAnimationBuilder.Builder setAnimationListener(Animation.AnimationListener listener) {
            this.listener = listener;
            return this;
        }

    }


}

