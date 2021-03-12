package com.jinhanexample.broadcast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jinhanexample.R
import kotlinx.android.synthetic.main.activity_broad_cast_base.*

class BroadCastBaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broad_cast_base)


        btnBroadCastJava.setOnClickListener {
            startActivity(Intent(this, BroadCastActivityJava::class.java))
        }

        btnBroadCastKotlin.setOnClickListener {
            startActivity(Intent(this, BroadCastActivityKotlin::class.java))
        }
    }
}