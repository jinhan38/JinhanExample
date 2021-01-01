package com.jinhanexample.clonePackageReading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jinhanexample.R

class ReadingIsPowerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reading_is_power)
        supportActionBar?.hide()
    }

}