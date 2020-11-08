package com.jinhanexample.scrollview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivityCollapsingToolbarLayoutBinding


class CollapsingToolbarLayout : AppCompatActivity() {

    lateinit var b : ActivityCollapsingToolbarLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_collapsing_toolbar_layout)


        //폰트변경
//        CollapsingToolbarLayout toolbar_layout;
//toolbar_layout = findViewById(R.id.toolbar_layout);
//
//Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/폰트파일명.ttf");
//
//toolbar_layout.setCollapsedTitleTypeface(tf); // 작아졌을 때
        setToolbar()

    }

    
    private fun setToolbar() {
        b.toolbar.title = "Collapsing toolbar layout"
        setSupportActionBar(b.toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        b.toolbar.setNavigationOnClickListener { v -> onBackPressed() }
    }
}