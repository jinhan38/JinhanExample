package com.jinhanexample.video

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivityVideoBaseBinding

class VideoBaseActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var b: ActivityVideoBaseBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityVideoBaseBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnMediaPlayerJava.setOnClickListener(this)
        b.btnMediaPlayerKotlin.setOnClickListener(this)
        b.btnExoPlayerJava.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnMediaPlayerJava -> {
                startActivity(Intent(this, VideoAudioFocusControlJava::class.java))
            }
            R.id.btnMediaPlayerKotlin -> {
                startActivity(Intent(this, VideoAudioFocusControlKotlin::class.java))
            }
            R.id.btnExoPlayerJava -> {
                startActivity(Intent(this, ExoPlayerActivity::class.java))
            }
        }
    }
}