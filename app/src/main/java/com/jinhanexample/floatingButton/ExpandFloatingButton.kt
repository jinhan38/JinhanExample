package com.jinhanexample.floatingButton

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jinhanexample.R
import java.util.*
import kotlin.concurrent.timerTask


//import com.jinhanexample.databinding.ActivityExpandFloatingButtonBinding

class ExpandFloatingButton : AppCompatActivity() {

//    lateinit var b: ActivityExpandFloatingButtonBinding

    companion object {
        private const val TAG = "ExpandFloatingButton"
    }

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expand_floating_button)

        var floating_button = findViewById<FloatingActionButton>(R.id.floating_button)
        var constraintLayout_motion = findViewById<MotionLayout>(R.id.constraintLayout_motion)

//        var extendedFab = FloatingButtonCallback()
//
//
//        var show: TimerTask = timerTask {
//            extended_floating_button.visibility = View.VISIBLE
//            extended_floating_button.extend()
//            floating_button.visibility = View.GONE
//        }
//
//
//        var hide: TimerTask = timerTask {
//            extendedFab.onShrunken(extended_floating_button)
//            extended_floating_button.visibility = View.VISIBLE
//            floating_button.visibility = View.GONE
//        }
//
//        var timer = Timer()
//
//        floating_button.setOnClickListener {
//            //펼쳐진 버튼이 보이게 해야함
//            Log.d(TAG, "onCreate: 클릭")
//            timer.schedule(show, 0, 300)
//        }
//
//
//        var startMotion: Int = 0
//        var endMotion: Int = 0
//        constraintLayout_motion.setTransitionListener(object : MotionLayout.TransitionListener {
//            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
//                Log.d(TAG, "onTransitionStarted: p1 : $p1, p2 : $p2")
//                startMotion = p1
//                endMotion = p2
//            }
//
//            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
////                Log.d(TAG, "onTransitionChange: ")
//            }
//
//            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
//                Log.d(TAG, "onTransitionCompleted: p1 : $p1")
//                if (p1 == startMotion) {
//                    Log.d(TAG, "onTransitionCompleted: p1 일치")
//                    extendedFab.onShrunken(extended_floating_button)
//                } else {
//                    Log.d(TAG, "onTransitionCompleted: p1 불일치")
//                    floating_button.visibility = View.GONE
//                    extended_floating_button.visibility = View.VISIBLE
//                    extended_floating_button.extend()
//
//                }
//            }
//
//
//            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
//                Log.d(TAG, "onTransitionTrigger: p1 : $p1, p2 : $p2, p3 : $p3")
//            }
//
//        })

//        extended_floating_button.setOnClickListener {
//            extendedFab.onShrunken(extended_floating_button)
//            timer.schedule(hide, 0, 300)
//        }


    }


}

//class FloatingButtonCallback : ExtendedFloatingActionButton.OnChangedCallback() {
//
//    override fun onShrunken(extendedFab: ExtendedFloatingActionButton?) {
//        super.onShrunken(extendedFab)
//        Log.d("TAG", "onShrunken: ")
//    }
//
//
//    override fun onShown(extendedFab: ExtendedFloatingActionButton?) {
//        super.onShown(extendedFab)
//        extendedFab?.extend()
//    }
//}