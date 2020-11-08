package com.jinhanexample.scrollview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivityScrollingShowAnimationBinding

class ScrollingShowAnimation : AppCompatActivity() {

    companion object {
        private const val TAG = "ScrollingShowAnimation"
    }

    lateinit var b: ActivityScrollingShowAnimationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_scrolling_show_animation)


        b.scrollView.setOnScrollChangeListener { view, i, i2, i3, i4 ->
            var percentScroll =
                b.scrollView.scrollY.toDouble() / b.scrollView.maxScrollAmount.toDouble()
            Log.d(TAG, "onCreate: percentScroll : $percentScroll")
            Log.d(TAG, "onCreate: scrollY : ${b.scrollView.scrollY.toDouble()}")
            Log.d(TAG, "onCreate: 맥스 : ${b.scrollView.maxScrollAmount.toDouble()}")

            Log.d(TAG, "onCreate:  top : ${b.fifth.top} ")

            

        }



        b.scrollView.run {
            fifth = b.fifth
        }
    }
}