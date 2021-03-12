package com.jinhanexample.string

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jinhanexample.R
import kotlinx.android.synthetic.main.activity_string_base.*

class StringBaseActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_string_base)

        btnStringFormat.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {

        when (p0!!.id) {
            R.id.btnStringFormat -> {
                startActivity(Intent(this, StringFormatActivity::class.java))
            }
        }
    }
}