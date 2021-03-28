package com.jinhanexample.touch

import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.R

class TouchTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch_test)

        val customLayout = CustomLayout(this)
        addContentView(customLayout, FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT))

        val customView = CustomView(this)
        customView.text = "Hello world!"
        customLayout.addView(customView)

    }
}