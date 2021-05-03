package com.jinhanexample.animation.splashAnim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.animation.AccelerateInterpolator
import androidx.databinding.DataBindingUtil
import com.jinhanexample.Common
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivitySplashAnimFirstBinding

class SplashAnimFirstActivity : AppCompatActivity() {

    var from_height = 0
    var from_width = 0
    var from_x = 0f
    var from_y = 0f
    var to_height = 0
    var to_width = 0
    var to_x = 0f
    var to_y = 0f
    var distance_y = 0f
    lateinit var binding: ActivitySplashAnimFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_anim_first)

        binding.startAnimation.setOnClickListener {

            moveView()

            Handler(mainLooper).postDelayed({
                startActivity(Intent(this, SplashAnimSecondActivity::class.java))
                finish()
            }, 1000)


        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        getViewPosition()
    }


    /**
     * 이동할 데이터의 좌표 가져오기
     */
    private fun getViewPosition() {
        from_width = binding.moveView.width
        from_height = binding.moveView.height
        from_x = binding.moveView.x + from_width / 2
        from_y = binding.moveView.y + from_height / 2

        to_width = binding.btnSplashFirst.width
        to_height = binding.btnSplashFirst.height
        to_x = binding.btnSplashFirst.x + to_width / 2
        to_y = binding.btnSplashFirst.y + to_height / 2

        distance_y = to_y - from_y    //현재 from_view의 위치부터 to_view의 위치까지의 거리
        Log.d(TAG, "getViewPosition: distance_y : $distance_y")

    }


    private fun moveView() {

        binding.moveView.apply {

            animate().alpha(0.5f).translationY(distance_y)
                .scaleX(0.1f)
                .scaleY(0.1f)
                .setDuration(500)
                .setInterpolator(AccelerateInterpolator())
                .withEndAction {

                    //translationY = 0f 값을 주면 이동 전의 원래 위치를 의미
                    animate().alpha(1f)
                        .scaleX(0.25f)
                        .scaleY(0.5f)
//                        .x(Common.getDP(this@SplashAnimFirstActivity, 100))
//                        .y(Common.getDP(this@SplashAnimFirstActivity, 50))
                        .setDuration(500)
                        .setInterpolator(AccelerateInterpolator())
                }

        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    companion object {
        private const val TAG = "SplashAnimFirstActivity"
    }
}