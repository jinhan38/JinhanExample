package com.jinhanexample.jetBrain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jinhanexample.R

class JetBrainBaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jet_brain_base)
    }
}