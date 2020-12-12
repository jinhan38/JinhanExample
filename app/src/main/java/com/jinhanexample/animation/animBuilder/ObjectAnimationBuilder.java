package com.jinhanexample.animation.animBuilder;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

public class ObjectAnimationBuilder {

    private ObjectAnimationBuilder(Builder builder) {
        View view = builder.view;
        int duration = builder.duration;
        Interpolator interpolator = builder.interpolator;
        int repeatMode = builder.repeatMode;
        int repeatCount = builder.repeatCount;

        ObjectAnimator objectAnimator = new ObjectAnimator();
//        objectAnimator.setProperty();
    }

    public static class Builder {
        private View view;
        private int duration = 0;
        private Interpolator interpolator = new AccelerateDecelerateInterpolator(); //default
        private int repeatMode = 0;
        private int repeatCount = 0;


    }
}
