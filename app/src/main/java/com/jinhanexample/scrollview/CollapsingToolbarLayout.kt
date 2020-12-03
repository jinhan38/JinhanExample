package com.jinhanexample.scrollview

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivityCollapsingToolbarLayoutBinding


class CollapsingToolbarLayout : AppCompatActivity() {

    lateinit var b: ActivityCollapsingToolbarLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_collapsing_toolbar_layout)

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