package com.jinhanexample.floatingButton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.motion.widget.MotionLayout
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jinhanexample.R

class FloatingAnimationActivity : AppCompatActivity() {
    companion object{
        private const val TAG = "FloatingAnimationActivi"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_floating_animation)

        var extended_floating_button =
            findViewById<ExtendedFloatingActionButton>(R.id.extend_floating_button)

        extended_floating_button.shrink()
        extended_floating_button.setOnClickListener {
            Log.d(TAG, "onCreate: 확장버튼 클릭")
            if (extended_floating_button.isExtended) {
                Log.d(TAG, "onCreate: 펼쳐짐")
                extended_floating_button.shrink()
            } else {
                Log.d(TAG, "onCreate: 접힘")
                extended_floating_button.extend()
            }
        }


    }
}