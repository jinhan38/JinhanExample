package com.jinhanexample.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.R
import kotlinx.android.synthetic.main.activity_broad_cast.*

class BroadCastActivityKotlin : AppCompatActivity() {

    companion object {
        final const val BROAD_CAST_MESSAGE: String = "broadCastTest"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broad_cast)


        val intentFilter = IntentFilter()
        intentFilter.addAction(BROAD_CAST_MESSAGE)
        registerReceiver(broadCastController, intentFilter)

        btnBroadCastTest.setOnClickListener {
            sendBroadcast(Intent(BROAD_CAST_MESSAGE))
        }
    }


    private val broadCastController: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == BROAD_CAST_MESSAGE) {
                Toast.makeText(this@BroadCastActivityKotlin, "broadCast test message", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


}