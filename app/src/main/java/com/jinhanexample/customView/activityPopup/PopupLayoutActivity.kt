package com.jinhanexample.customView.activityPopup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jinhanexample.R
import kotlinx.android.synthetic.main.activity_popup_layout.*

class PopupLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup_layout)

        tvShowPopup.setOnClickListener {
            startActivity(Intent(this, ShowPopup::class.java))
        }

    }

}