package com.jinhanexample.customView

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.R
import com.jinhanexample.customView.compoundView.CompoundViewActivity
import kotlinx.android.synthetic.main.activity_custom_view.*

class CustomViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view)

        compoundView.setOnClickListener {
            startActivity(Intent(this, CompoundViewActivity::class.java))
        }
    }
}