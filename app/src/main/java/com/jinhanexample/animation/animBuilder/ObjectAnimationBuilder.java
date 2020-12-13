package com.jinhanexample.animation.animBuilder;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

public class ObjectAnimationBuilder {

    private ObjectAnimationBuilder(Builder builder) {
        View view = builder.view;
        String type = builder.type;
        float value = builder.value;
        int duration = builder.duration;
        Interpolator interpolator = builder.interpolator;
        int repeatMode = builder.repeatMode;
        int repeatCount = builder.repeatCount;
        int startDelay = builder.startDelay;
        int visible = builder.visible;
        Animator.AnimatorListener listener = builder.listener;


        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, type, value);
        objectAnimator.setDuration(duration);
        objectAnimator.setRepeatCount(repeatCount);
        objectAnimator.setRepeatMode(repeatMode);
        objectAnimator.setInterpolator(interpolator);
        objectAnimator.setStartDelay(startDelay);
        if (listener != null) objectAnimator.addListener(listener);
        objectAnimator.start();

        view.setVisibility(visible);

    }

    public static class Builder {
        private View view;
        private String type = "";
        private float value = 0f;
        private int duration = 0;
        private Interpolator interpolator = new AccelerateDecelerateInterpolator(); //default
        private int repeatMode = 0;
        private int repeatCount = 0;
        private int startDelay = 0;
        private int visible = View.VISIBLE;
        private Animator.AnimatorListener listener = null;


        public ObjectAnimationBuilder build() {
            return new ObjectAnimationBuilder(this);
        }


        public Builder(View view, int duration, String type, float value) {
            this.view = view;
            this.duration = duration;
            this.type = type;
            this.value = value;
        }

        public Builder setListener(Animator.AnimatorListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder setInterpolator(Interpolator interpolator) {
            this.interpolator = interpolator;
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

        public Builder setStartDelay(int startDelay) {
            this.startDelay = startDelay;
            return this;
        }

        public Builder setVisibility(int visible) {
            this.visible = visible;
            return this;
        }
    }

}
