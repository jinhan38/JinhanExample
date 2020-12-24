package com.jinhanexample.draw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.jinhanexample.R
import com.jinhanexample.draw.layoutParams.DrawLayoutParamsActivity
import com.jinhanexample.draw.line.DrawLineActivity
import com.jinhanexample.draw.rect.RectActivity

class DrawActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw)

        findViewById<Button>(R.id.layoutParams).setOnClickListener {
            startActivity(Intent(this, DrawLayoutParamsActivity::class.java))
        }

        findViewById<Button>(R.id.rect).setOnClickListener {
            startActivity(Intent(this, RectActivity::class.java))
        }

        findViewById<Button>(R.id.line).setOnClickListener {
            startActivity(Intent(this, DrawLineActivity::class.java))
        }
    }
}