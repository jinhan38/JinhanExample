package com.jinhanexample.service

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jinhanexample.R
import kotlinx.android.synthetic.main.activity_service_base.*

class ServiceBaseActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_base)

        btnMusicPlayerService.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.btnMusicPlayerService -> {

            }
        }
    }
}