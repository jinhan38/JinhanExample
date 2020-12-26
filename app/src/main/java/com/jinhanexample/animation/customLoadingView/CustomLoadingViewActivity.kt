package com.jinhanexample.animation.customLoadingView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivityCustomLoadingViewBinding

class CustomLoadingViewActivity : AppCompatActivity() {

    private lateinit var ui: ActivityCustomLoadingViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ui = ActivityCustomLoadingViewBinding.inflate(layoutInflater).apply { setContentView(root) }
        ui.showLoading.setOnClickListener { ui.loading.showLoading() }
        ui.hideLoading.setOnClickListener { ui.loading.hideLoading() }
    }
}