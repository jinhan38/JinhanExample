package com.jinhanexample.fragment.callback

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.jinhanexample.R

class FragmentCallBackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_call_back)

        var imageFragment =
            supportFragmentManager.findFragmentById(R.id.image_fragment) as ImageCallBackFragment

        /**
         * 여기서 생성한  OnImageClickListener 콜백 함수를 넣어서 보내준다.
         *
         * fragment에서 imageView와 message를 넘겨준다.
         * 여기서는 넘겨받는 argument들로 어떤것을 할지 정의하면 된다.
         */
        imageFragment.setOnImageClickListener(object : ImageCallBackFragment.OnImageClickListener {
            override fun onImageClick(view: ImageView, message: String) {

                Toast.makeText(this@FragmentCallBackActivity, message, Toast.LENGTH_SHORT).show()

            }
        })

    }
}