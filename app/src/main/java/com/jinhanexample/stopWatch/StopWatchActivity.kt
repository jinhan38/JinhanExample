package com.jinhanexample.stopWatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer
import com.jinhanexample.R

class StopWatchActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var mChrono : Chronometer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stop_watch)

        mChrono = findViewById(R.id.chrono)
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.btnstart ->{

                mChrono.start()
            }
            R.id.btnstop ->{
                mChrono.stop()

            }
            R.id.btnreset ->{
                mChrono.base = SystemClock.elapsedRealtime();

            }
        }
    }
}