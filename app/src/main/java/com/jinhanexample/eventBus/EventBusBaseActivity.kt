package com.jinhanexample.eventBus

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.R
import kotlinx.android.synthetic.main.activity_event_bus_base.*

class EventBusBaseActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus_base)

        btnEventBusSampleKotlin.setOnClickListener(this)
        btnEventBusSampleJava.setOnClickListener(this)


    }

    override fun onClick(p0: View?) {

        when (p0!!.id) {

            R.id.btnEventBusSampleKotlin -> {
                startActivity(Intent(this, EventBusSampleActivityKotlin::class.java))
            }

            R.id.btnEventBusSampleJava -> {
                startActivity(Intent(this, EventBusSampleActivityJava::class.java))
            }
        }
    }

}