package com.jinhanexample.animation.animBuilder;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jinhanexample.R;

public class AnimBuilderClassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_builder_class);

        ImageView imageView = findViewById(R.id.catImage);
        Button alphaAnim = findViewById(R.id.alphaAnim);
        Button translateAnim = findViewById(R.id.translateAnim);

        alphaAnim.setOnClickListener(view -> {
            new AlphaAnimationBuilder.Builder(imageView, 1.0f, 0.3f, 2000)
                    .setFillAfter(true)
                    .setRepeatCount(3)
                    .setInterpolator(new AccelerateInterpolator())
                    .setStartOffset(1500)
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
                    1500)
                    .setRepeatMode(TranslateAnimation.REVERSE)
                    .setInterpolator(new AccelerateInterpolator())
                    .setStartOffset(300)
                    .setRepeatCount(3)
                    .build();
        });
    }
}
