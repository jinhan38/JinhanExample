package com.jinhanexample.animation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.jinhanexample.R

class ImageSlideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_slide)

        var viewpager = findViewById<ViewPager>(R.id.viewPager)
        var imageAdapter = ImageAdapter(this)
        viewpager.adapter = imageAdapter
    }
}