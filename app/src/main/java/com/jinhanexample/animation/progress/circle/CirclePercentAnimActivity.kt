package com.jinhanexample.animation.progress.circle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.databinding.ActivityCirclePercentAnimBinding

class CirclePercentAnimActivity : AppCompatActivity() {

    lateinit var b : ActivityCirclePercentAnimBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityCirclePercentAnimBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.button.setOnClickListener {
        }

    }
}