package com.jinhanexample.animation.animBuilder;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jinhanexample.R;
import com.jinhanexample.Common;

public class AnimBuilderClassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_builder_class);

        ImageView imageView = findViewById(R.id.catImage);
        Button alphaAnim = findViewById(R.id.alphaAnim);
        Button translateAnim = findViewById(R.id.translateAnim);
        Button objectAnim = findViewById(R.id.objectAnim);


        objectAnim.setOnClickListener(view -> {

            //x는 좌우, y는 상하, rotation은 회전
            ObjectAnimator objectAnimator =
                    ObjectAnimator.ofFloat(imageView, "x", Common.Companion.getDP(this, 100));
            objectAnimator.setDuration(2000);
            objectAnimator.start();

        });


        alphaAnim.setOnClickListener(view -> {
            new AlphaAnimationBuilder.Builder(imageView, 1.0f, 0.3f, 1000)
                    .setFillAfter(true)
                    .setRepeatCount(3)
                    .setInterpolator(new AccelerateInterpolator())
                    .setStartOffset(500)
                    .setRepeatMode(AlphaAnimation.REVERSE)
                    .build();
        });

        translateAnim.setOnClickListener(view -> {

            int animationType = Animation.RELATIVE_TO_SELF;// 본인 뷰를 기준으로하여 퍼센트 값으로 입력
//            int animationType = Animation.RELATIVE_TO_PARENT;// 부모뷰 뷰를 기준으로하여 퍼센트 값으로 입력
//            int animationType = Animation.ABSOLUTE;// 절대값으로 입력

            new TranslateAnimationBuilder.Builder(imageView,
                    animationType, 0,
                    animationType, 0,
                    animationType, 0,
                    animationType, 0.5f,
                    1000)
                    .setRepeatMode(TranslateAnimation.REVERSE)
                    .setInterpolator(new AccelerateInterpolator())
                    .setStartOffset(300)
                    .setRepeatCount(3)
                    .build();
        });
    }
}
