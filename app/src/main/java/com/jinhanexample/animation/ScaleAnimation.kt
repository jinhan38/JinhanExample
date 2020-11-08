package com.jinhanexample.animation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jinhanexample.R

class ScaleAnimation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scale_animation)

        var fab_scale_animation: FloatingActionButton = findViewById(R.id.fab_scale_animation)
        var animation: Animation = AnimationUtils.loadAnimation(this, R.anim.scale_animation)
        fab_scale_animation.startAnimation(animation)


    }
}