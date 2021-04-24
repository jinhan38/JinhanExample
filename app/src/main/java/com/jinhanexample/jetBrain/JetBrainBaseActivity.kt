package com.jinhanexample.jetBrain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.jinhanexample.R
import com.jinhanexample.jetBrain.paging.PagingLibraryActivity

class JetBrainBaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jet_brain_base)

        findViewById<TextView>(R.id.pagingLibrary).setOnClickListener {
            startActivity(Intent(this, PagingLibraryActivity::class.java))
        }
    }
}